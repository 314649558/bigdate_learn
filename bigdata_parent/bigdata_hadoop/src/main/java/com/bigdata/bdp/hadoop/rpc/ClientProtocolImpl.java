package com.bigdata.bdp.hadoop.rpc;

import org.apache.hadoop.crypto.CryptoProtocolVersion;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.AclEntry;
import org.apache.hadoop.fs.permission.AclStatus;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hdfs.AddBlockFlag;
import org.apache.hadoop.hdfs.inotify.EventBatchList;
import org.apache.hadoop.hdfs.protocol.*;
import org.apache.hadoop.hdfs.security.token.block.DataEncryptionKey;
import org.apache.hadoop.hdfs.security.token.delegation.DelegationTokenIdentifier;
import org.apache.hadoop.hdfs.server.protocol.DatanodeStorageReport;
import org.apache.hadoop.io.EnumSetWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.token.Token;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Administrator on 2020/12/5.
 */
public class ClientProtocolImpl implements ClientProtocol {
    @Override
    public LocatedBlocks getBlockLocations(String s, long l, long l1) throws IOException {
        return null;
    }

    @Override
    public FsServerDefaults getServerDefaults() throws IOException {
        return null;
    }

    @Override
    public HdfsFileStatus create(String s, FsPermission fsPermission, String s1, EnumSetWritable<CreateFlag> enumSetWritable, boolean b, short i, long l, CryptoProtocolVersion[] cryptoProtocolVersions) throws IOException {
        return null;
    }

    @Override
    public LastBlockWithStatus append(String s, String s1, EnumSetWritable<CreateFlag> enumSetWritable) throws IOException {
        return null;
    }

    @Override
    public boolean setReplication(String s, short i) throws IOException {
        return false;
    }

    @Override
    public BlockStoragePolicy[] getStoragePolicies() throws IOException {
        return new BlockStoragePolicy[0];
    }

    @Override
    public void setStoragePolicy(String s, String s1) throws IOException {

    }

    @Override
    public void unsetStoragePolicy(String s) throws IOException {

    }

    @Override
    public BlockStoragePolicy getStoragePolicy(String s) throws IOException {
        return null;
    }

    @Override
    public void setPermission(String s, FsPermission fsPermission) throws IOException {

    }

    @Override
    public void setOwner(String s, String s1, String s2) throws IOException {

    }

    @Override
    public void abandonBlock(ExtendedBlock extendedBlock, long l, String s, String s1) throws IOException {

    }

    @Override
    public LocatedBlock addBlock(String s, String s1, ExtendedBlock extendedBlock, DatanodeInfo[] datanodeInfos, long l, String[] strings, EnumSet<AddBlockFlag> enumSet) throws IOException {
        return null;
    }

    @Override
    public LocatedBlock getAdditionalDatanode(String s, long l, ExtendedBlock extendedBlock, DatanodeInfo[] datanodeInfos, String[] strings, DatanodeInfo[] datanodeInfos1, int i, String s1) throws IOException {
        return null;
    }

    @Override
    public boolean complete(String s, String s1, ExtendedBlock extendedBlock, long l) throws IOException {
        return false;
    }

    @Override
    public void reportBadBlocks(LocatedBlock[] locatedBlocks) throws IOException {

    }

    @Override
    public boolean rename(String s, String s1) throws IOException {
        return false;
    }

    @Override
    public void concat(String s, String[] strings) throws IOException {

    }

    @Override
    public void rename2(String s, String s1, Options.Rename... renames) throws IOException {

    }

    @Override
    public boolean truncate(String s, long l, String s1) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String s, boolean b) throws IOException {
        return false;
    }

    @Override
    public boolean mkdirs(String s, FsPermission fsPermission, boolean b) throws IOException {
        return false;
    }

    @Override
    public DirectoryListing getListing(String s, byte[] bytes, boolean b) throws IOException {
        return null;
    }

    @Override
    public SnapshottableDirectoryStatus[] getSnapshottableDirListing() throws IOException {
        return new SnapshottableDirectoryStatus[0];
    }

    @Override
    public void renewLease(String s) throws IOException {
        System.out.println(s+" 真厉害");
    }

    @Override
    public boolean recoverLease(String s, String s1) throws IOException {
        return false;
    }

    @Override
    public long[] getStats() throws IOException {
        return new long[0];
    }

    @Override
    public DatanodeInfo[] getDatanodeReport(HdfsConstants.DatanodeReportType datanodeReportType) throws IOException {
        return new DatanodeInfo[0];
    }

    @Override
    public DatanodeStorageReport[] getDatanodeStorageReport(HdfsConstants.DatanodeReportType datanodeReportType) throws IOException {
        return new DatanodeStorageReport[0];
    }

    @Override
    public long getPreferredBlockSize(String s) throws IOException {
        return 0;
    }

    @Override
    public boolean setSafeMode(HdfsConstants.SafeModeAction safeModeAction, boolean b) throws IOException {
        return false;
    }

    @Override
    public void saveNamespace() throws IOException {

    }

    @Override
    public long rollEdits() throws IOException {
        return 0;
    }

    @Override
    public boolean restoreFailedStorage(String s) throws IOException {
        return false;
    }

    @Override
    public void refreshNodes() throws IOException {

    }

    @Override
    public void finalizeUpgrade() throws IOException {

    }

    @Override
    public RollingUpgradeInfo rollingUpgrade(HdfsConstants.RollingUpgradeAction rollingUpgradeAction) throws IOException {
        return null;
    }

    @Override
    public CorruptFileBlocks listCorruptFileBlocks(String s, String s1) throws IOException {
        return null;
    }

    @Override
    public void metaSave(String s) throws IOException {

    }

    @Override
    public void setBalancerBandwidth(long l) throws IOException {

    }

    @Override
    public HdfsFileStatus getFileInfo(String s) throws IOException {
        return null;
    }

    @Override
    public boolean isFileClosed(String s) throws IOException {
        return false;
    }

    @Override
    public HdfsFileStatus getFileLinkInfo(String s) throws IOException {
        return null;
    }

    @Override
    public ContentSummary getContentSummary(String s) throws IOException {
        return null;
    }

    @Override
    public void setQuota(String s, long l, long l1, StorageType storageType) throws IOException {

    }

    @Override
    public void fsync(String s, long l, String s1, long l1) throws IOException {

    }

    @Override
    public void setTimes(String s, long l, long l1) throws IOException {

    }

    @Override
    public void createSymlink(String s, String s1, FsPermission fsPermission, boolean b) throws IOException {

    }

    @Override
    public String getLinkTarget(String s) throws IOException {
        return null;
    }

    @Override
    public LocatedBlock updateBlockForPipeline(ExtendedBlock extendedBlock, String s) throws IOException {
        return null;
    }

    @Override
    public void updatePipeline(String s, ExtendedBlock extendedBlock, ExtendedBlock extendedBlock1, DatanodeID[] datanodeIDS, String[] strings) throws IOException {

    }

    @Override
    public Token<DelegationTokenIdentifier> getDelegationToken(Text text) throws IOException {
        return null;
    }

    @Override
    public long renewDelegationToken(Token<DelegationTokenIdentifier> token) throws IOException {
        return 0;
    }

    @Override
    public void cancelDelegationToken(Token<DelegationTokenIdentifier> token) throws IOException {

    }

    @Override
    public DataEncryptionKey getDataEncryptionKey() throws IOException {
        return null;
    }

    @Override
    public String createSnapshot(String s, String s1) throws IOException {
        return null;
    }

    @Override
    public void deleteSnapshot(String s, String s1) throws IOException {

    }

    @Override
    public void renameSnapshot(String s, String s1, String s2) throws IOException {

    }

    @Override
    public void allowSnapshot(String s) throws IOException {

    }

    @Override
    public void disallowSnapshot(String s) throws IOException {

    }

    @Override
    public SnapshotDiffReport getSnapshotDiffReport(String s, String s1, String s2) throws IOException {
        return null;
    }

    @Override
    public long addCacheDirective(CacheDirectiveInfo cacheDirectiveInfo, EnumSet<CacheFlag> enumSet) throws IOException {
        return 0;
    }

    @Override
    public void modifyCacheDirective(CacheDirectiveInfo cacheDirectiveInfo, EnumSet<CacheFlag> enumSet) throws IOException {

    }

    @Override
    public void removeCacheDirective(long l) throws IOException {

    }

    @Override
    public BatchedRemoteIterator.BatchedEntries<CacheDirectiveEntry> listCacheDirectives(long l, CacheDirectiveInfo cacheDirectiveInfo) throws IOException {
        return null;
    }

    @Override
    public void addCachePool(CachePoolInfo cachePoolInfo) throws IOException {

    }

    @Override
    public void modifyCachePool(CachePoolInfo cachePoolInfo) throws IOException {

    }

    @Override
    public void removeCachePool(String s) throws IOException {

    }

    @Override
    public BatchedRemoteIterator.BatchedEntries<CachePoolEntry> listCachePools(String s) throws IOException {
        return null;
    }

    @Override
    public void modifyAclEntries(String s, List<AclEntry> list) throws IOException {

    }

    @Override
    public void removeAclEntries(String s, List<AclEntry> list) throws IOException {

    }

    @Override
    public void removeDefaultAcl(String s) throws IOException {

    }

    @Override
    public void removeAcl(String s) throws IOException {

    }

    @Override
    public void setAcl(String s, List<AclEntry> list) throws IOException {

    }

    @Override
    public AclStatus getAclStatus(String s) throws IOException {
        return null;
    }

    @Override
    public void createEncryptionZone(String s, String s1) throws IOException {

    }

    @Override
    public EncryptionZone getEZForPath(String s) throws IOException {
        return null;
    }

    @Override
    public BatchedRemoteIterator.BatchedEntries<EncryptionZone> listEncryptionZones(long l) throws IOException {
        return null;
    }

    @Override
    public void setXAttr(String s, XAttr xAttr, EnumSet<XAttrSetFlag> enumSet) throws IOException {

    }

    @Override
    public List<XAttr> getXAttrs(String s, List<XAttr> list) throws IOException {
        return null;
    }

    @Override
    public List<XAttr> listXAttrs(String s) throws IOException {
        return null;
    }

    @Override
    public void removeXAttr(String s, XAttr xAttr) throws IOException {

    }

    @Override
    public void checkAccess(String s, FsAction fsAction) throws IOException {

    }

    @Override
    public long getCurrentEditLogTxid() throws IOException {
        return 0;
    }

    @Override
    public EventBatchList getEditsFromTxid(long l) throws IOException {
        return null;
    }

    @Override
    public QuotaUsage getQuotaUsage(String s) throws IOException {
        return null;
    }
}
