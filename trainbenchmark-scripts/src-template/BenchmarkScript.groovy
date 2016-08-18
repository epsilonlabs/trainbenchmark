import static hu.bme.mit.trainbenchmark.constants.RailwayOperation.*
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfigWrapper
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkRunner
import hu.bme.mit.trainbenchmark.constants.Scenario
import BenchmarkReporter

def xms = "12G"
def xmx = "12G"
def minSize = 1
def maxSize = 2
def timeout = 900
def runs = 3
def queryTransformationCount = 5

def operations = [
	RailwayOperation.POSLENGTH_REPAIR,
	RailwayOperation.ROUTESENSOR,
	RailwayOperation.SWITCHMONITORED_REPAIR
]

for (scenario in scenarios) {
	def scenarioString = scenario.toString().toLowerCase()
	def messageSize = 2048

//	BenchmarkRunner.run(new BlazegraphBenchmarkConfigWrapper(bc, false))
//	BenchmarkRunner.run(new EclipseOclBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new DroolsBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new EmfApiBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new JenaBenchmarkConfigWrapper(bc, false))
//	BenchmarkRunner.run(new MySqlBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.COREAPI))
//	BenchmarkRunner.run(new Neo4jBenchmarkConfigWrapper(bc, Neo4jEngine.CYPHER))
//	BenchmarkRunner.run(new Rdf4jBenchmarkConfigWrapper(bc, false))
//	BenchmarkRunner.run(new SQLiteBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new TinkerGraphBenchmarkConfigWrapper(bc))
//	BenchmarkRunner.run(new ViatraBenchmarkConfigWrapper(bc))

}

BenchmarkReporter.reportReady()
