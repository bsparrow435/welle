(ns clojurewerkz.welle.buckets
  (:refer-clojure :exclude [list])
  (:use clojurewerkz.welle.core
        clojurewerkz.welle.conversion)
  (:import [com.basho.riak.client IRiakClient IRiakObject]
           [com.basho.riak.client.bucket Bucket WriteBucket]
           [com.basho.riak.client.http.response BucketResponse ListBucketsResponse]
           [com.basho.riak.client.operations StoreObject FetchObject]
           [com.basho.riak.client.cap ConflictResolver Retrier]
           [com.basho.riak.client.convert Converter]
           [com.basho.riak.client.cap Quora Quorum]))


;;
;; API
;;

(defn fetch
  "Fetches bucket properties"
  [^String bucket-name]
  (merge {:name bucket-name}
         (from-bucket-properties (.fetchBucket *riak-client* bucket-name))))

(defn update
  "Updates bucket properties.

   Quorum values (r, w, dw and so on) can be integer, Quora or Quorum instances

   Options:

   * allow-siblings
   * last-write-wins
   * n-val (default: 3)
   * r (quorum value)
   * pr (quorum value)
   * w (quorum value)
   * dw (quorum value)
   * pw (quorum value)
   * rw (quorum value)
   * not-found-ok
   * basic-quorum
   * enable-for-search (default: false)
   * backend
   * pre-commit-hooks (a collection of pairs [\"erlang_module\", \"fn_name\"])
   * post-commit-hooks (a collection of pairs [\"erlang_module\", \"fn_name\"])
   * small-vclock
   * big-vclock
   * young-vclock
   * old-vclock"
  [^String bucket-name &{ :keys [allow-siblings last-write-wins n-val ^String backend
                                 small-vclock big-vclock young-vclock old-vclock
                                 r pr w dw pw rw
                                 ^Boolean not-found-ok ^Boolean basic-quorum ^Boolean enable-for-search
                                 pre-commit-hooks
                                 post-commit-hooks] :as options}]
  (.updateBucket *riak-client* bucket-name (to-bucket-properties (or options {})))
  (merge {:name bucket-name}
         (from-bucket-properties (.fetchBucket *riak-client* bucket-name))))

(defn ^{:deprecated true} create
  "The same as update. This name reveals the intent a bit better in some cases.
   Kept for backwards compatibility, will be removed in the future"
  [& args]
  (apply update args))

(defn list
  "Returns buckets in the cluster as a set"
  []
  (set (.listBuckets *riak-client*)))


(defn keys-in
  "Returns list of keys in the bucket. With any non-trivial number of keys, this is a VERY EXPENSIVE operation
   and typically should be avoided"
  [^String bucket-name]
  (.listKeys *riak-client* bucket-name))
