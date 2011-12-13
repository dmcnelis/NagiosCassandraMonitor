package me.mcnelis.cassandra.nagios;

import me.mcnelis.cassandra.nagios.util.Constants;

public enum CassandraObjects {
	STORAGE_SERVICE {
		public String toString() {
			return Constants.STORAGE_SERVICE;
		}
	},
	
	STORAGE_PROXY {
		public String toString() {
			return Constants.STORAGE_PROXY;
		}
	},
	
	COMPACTION_MGR {
		public String toString() {
			return Constants.COMPACTION_MGR;
		}
	},
	
	COMMIT_LOG {
		public String toString() {
			return Constants.COMMIT_LOG;
		}
	},
	
	STREAMING {
		public String toString() {
			return Constants.STREAMING;
		}
	}
}
