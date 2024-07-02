$currentDateTime = Get-Date -Format "yyyyMMddHHmm"
$jarPath = "./build/libs/opt-dash-batch-0.0.1-SNAPSHOT.jar"
$jobName = "powerScheduleJob"

java -jar $jarPath --spring.batch.job.name=$jobName version=$currentDateTime