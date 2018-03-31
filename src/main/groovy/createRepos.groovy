import org.sonatype.nexus.blobstore.api.BlobStore
import org.sonatype.nexus.repository.Repository

if (blobStore.blobStoreManager.browse().find { BlobStore blobStore -> blobStore.blobStoreConfiguration.name == 'yum' } == null) {
    blobStore.createFileBlobStore("yum", "/opt/sonatype-work/nexus3/blobs/yum")
}

if (repository.repositoryManager.browse().find { Repository repo -> repository.name == "test" } == null) {
    repository.createYumHosted("test", "yum")
}
