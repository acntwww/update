package me.reezy.cosmo.update

class UpdateAgent(
    val info: UpdateInfo,
    private val task: DownloadTask,
    private val update: (UpdateInfo, DownloadTask) -> Unit
) {
    fun update() {
        UpdateManager.log("UpdateAgent update => $info")
        update(info, task)
    }
    fun cancel() {
        UpdateManager.log("UpdateAgent cancel => $info")
        task.isCancelled = true
    }
    fun ignore() {
        UpdateManager.log("UpdateAgent ignore => $info")
        task.isCancelled = true
        UpdateStore.ignoreHash = info.hash
    }

    fun addDownloadListener(listener: DownloadListener) {
        task.addListener(listener)
    }
}