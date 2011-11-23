package me.mcnelis.cassandra.nagios;

public enum CassandraObjects {
	STORAGE_SERVICE {
		public String toString() {
			return "org.apache.cassandra.db:type=StorageService";
		}
	},
	
	STORAGE_PROXY {
		public String toString() {
			return "org.apache.cassandra.db:type=StorageProxy";
		}
	},
	
	COMPACTION_MGR {
		public String toString() {
			return "org.apache.cassandra.db:type=CompactionManager";
		}
	},
	
	COMMIT_LOG {
		public String toString() {
			return "org.apache.cassandra.db:type=CommitLog";
		}
	},
	
	STREAMING {
		public String toString() {
			return "org.apache.cassandra.streaming:type=StreamingService";
		}
	}
}
