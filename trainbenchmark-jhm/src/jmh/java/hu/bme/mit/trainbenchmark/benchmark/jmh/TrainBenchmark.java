package hu.bme.mit.trainbenchmark.benchmark.jmh;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.comparators.EpsilonMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonEvlEmfBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonEvlEmfBenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.driver.EpsilonDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.operations.EpsilonEvlModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import org.openjdk.jmh.annotations.*;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@Warmup(iterations = 3)
@Fork(value = 1, warmups = 1)
public class TrainBenchmark {

	@State(Scope.Thread)
	public static class MyState {

//		// FIXME How to pass parameters dynamically?
//		//@Param({ "100", "200", "300", "500", "1000" })
		public int modelSize = 1;
//
//		//@Param({ "inject", "repair" })
//		public String modelVariant = "inject";
//
//		public String workloadName = "Inject";
//
//		// FIXME modelSize must be an parameters
		public String injectModelFilename;
		public String repairModelFilename;

		// This should be parameters
		public RailwayOperation queryRO;
		public RailwayOperation injectRO;
		public RailwayOperation repairRO;
		public ModelOperation queryMO;


		// Each tool needs to provide its own:
		public EpsilonDriverFactory driverFactory;
		public EpsilonEvlModelOperationFactory modelOperationFactory;
		public EpsilonMatchComparator comparator = new EpsilonMatchComparator();
		public Driver injectDriver;
		public Driver repairDriver;

		public String test = "none";

		@Setup(Level.Trial)
		public void doSetup() throws Exception {
//			System.out.println("Do Setup");
			injectModelFilename = String.format("../models/railway-inject-%d", modelSize);
			repairModelFilename = String.format("../models/railway-repair-%d", modelSize);
//			int benchmarkId = ResultHelper.createNewResultDir();
//			ResultHelper.saveConfiguration(benchmarkId);
//
//			// FIXME Need this or constant and queryTransformationCount are not available
//			// Need al alternative BenchmarkConfigBaseBuilder implementation. We need to figure out how to run this test for each
//			// workload-modelSize combination
//			// Apparently what we need is actually a new JMHBenchmarkBundle or similar that is more fitted to the JMH, actually it seems
//			// that this class is the JMHBenchmarkBundle. In this case, the operations should also be parameters. Perhaps we can split this
//			// class into a query and a transform bundle since there are separate type of operations that must be done. Or not, becuase
//			// separating them will result in separate state and hence we can not share the EMFModel and EVL Engine....
//			System.out.println("configBuilder");
////			bcbb = new BenchmarkConfigBaseBuilder()
////				.setBenchmarkId(benchmarkId).setTimeout(timeout).setRuns(runs)
////				.setOperations(operations).setWorkload(workloadName)
////				.setQueryTransformationCount(queryTransformationCount).setTransformationConstant(constant)
////				.setTransformationChangeSetStrategy(strategy)
//
//			System.out.println("configBuilder");
			BenchmarkConfigBuilder configBuilder = new EpsilonEvlEmfBenchmarkConfigBuilder()
				.withEmfModel()
				.disposeAfterExecution(true);
//
//			System.out.println("benchmarkBundle");
			EpsilonEvlEmfBenchmarkConfig bc = (EpsilonEvlEmfBenchmarkConfig) configBuilder.createConfig();
			driverFactory = new EpsilonDriverFactory(bc);
			injectDriver = driverFactory.createInstance();
			injectDriver.initialize();
			repairDriver = driverFactory.createInstance();
			repairDriver.initialize();

			modelOperationFactory = new EpsilonEvlModelOperationFactory<>(bc.getEngineFinder());
			queryRO = RailwayOperation.CONNECTEDSEGMENTS;
			injectRO = RailwayOperation.CONNECTEDSEGMENTS_INJECT;
			repairRO = RailwayOperation.CONNECTEDSEGMENTS_REPAIR;

			queryMO = modelOperationFactory.createOperation(queryRO, "", (EpsilonDriver) injectDriver);
			System.out.println(test);

		}

		@TearDown(Level.Trial)
		public void doTearDown() {

		}

	}


	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	@Measurement(iterations = 2, time = 10, timeUnit = SECONDS)		// time is how long to invoke the benchmark, i.e. call the method as many times as possible in the time window
	public void _1_readPhase(MyState state) throws Exception {
		String modelPath = state.injectModelFilename + state.injectDriver.getPostfix();
		state.injectDriver.read(modelPath);
		modelPath = state.repairModelFilename + state.repairDriver.getPostfix();
		state.repairDriver.read(modelPath);
		if (!state.test.equals("read")) {
			System.out.println(state.test);
			state.test = "read";
		}

	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	@Measurement(iterations = 2, time = 10, timeUnit = SECONDS)
	public void _2_queryPhase(MyState state) throws Exception {
		if (!state.test.equals("query")) {
			System.out.println(state.test);
			state.test = "query";
		}
		state.queryMO.getQuery().evaluate();

	}
//
//	// FIXME In the benchmark this two methods are run queryTransformationCount times
//	@Benchmark
//	@BenchmarkMode(Mode.AverageTime)
//	@OutputTimeUnit(TimeUnit.MICROSECONDS)
//	//@Fork(value = 1, warmups = 1)
//	@Measurement(iterations = 10, time = 1, timeUnit = SECONDS)
//	public int _3_transformationPhase(MyState state) throws Exception {
//		int a = 1;
//		int b = 2;
//		int sum = a + b;
//
//		return sum;
//		// FIXME Shuffle should not be measured!
////		state.benchmarkBundle.shuffle();
////		state.benchmarkBundle.transform();
//	}
//
//	@Benchmark
//	@BenchmarkMode(Mode.AverageTime)
//	@OutputTimeUnit(TimeUnit.MICROSECONDS)
//	//@Fork(value = 1, warmups = 1)
//	@Measurement(iterations = 10, time = 1, timeUnit = SECONDS)
//	public int _4_requeryPhase(MyState state) throws Exception {
//		int a = 1;
//		int b = 2;
//		int sum = a + b;
//
//		return sum;
////		state.benchmarkBundle.query();
//	}
//
//
//	// FIXME run each time but dont measure
//	public void cleanupPhase(MyState state) throws Exception {
//		System.out.println("cleanupPhase");
////		state.benchmarkBundle.cleanup();
//	}
}

