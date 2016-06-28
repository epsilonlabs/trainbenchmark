import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.runner.BenchmarkRunner
import hu.bme.mit.trainbenchmark.constants.Scenario

def xms = "12G"
def xmx = "12G"
def minSize = 1
def maxSize = 1024
def timeout = 300
def runs = 3
def queryTransformationCount = 1

def scenarios = [
	//	Scenario.BATCH,
	//	Scenario.INJECT,
	Scenario.REPAIR,
]

for (scenario in scenarios) {
	for (size = minSize; size <= maxSize; size*=2) {
		def scenarioString = scenario.toString().toLowerCase()
		def modelPath = "railway-${scenarioString}-${size}"
		def messageSize = 10

		def operations1 = [ROUTESENSOR_REPAIR]
		def bcRouteSensor = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations1, "RouteSensor")
		for (variant in ["A", "B"]) {
			BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcRouteSensor, messageSize, variant))
		}
		
		def operations2 = [CONNECTEDSEGMENTS_REPAIR]
		def bcConnectedSegments = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelPath, operations2, "ConnectedSegments")
		for (variant in ["A", "B", "C", "D"]) {
			BenchmarkRunner.run(new IQDBenchmarkConfigWrapper(bcConnectedSegments, messageSize, variant))
		}
	}
}

//		BenchmarkRunner.run(new BlazegraphBenchmarkConfigWrapper(bc, false))
//		BenchmarkRunner.run(new EclipseOclBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new Drools5BenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new Drools6BenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new EmfApiBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new SesameBenchmarkConfigWrapper(bc, false))

//		BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bc, false))
//		BenchmarkRunner.run(new MySqlBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.COREAPI))
//		BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.CYPHER))
//		BenchmarkRunner.run(new SQLiteBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new TinkerGraphBenchmarkConfigWrapper(bc))
//		BenchmarkRunner.run(new ViatraBenchmarkConfigWrapper(bc))
