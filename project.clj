(defproject com.novemberain/welle "1.0.0-SNAPSHOT"
  :description "An experimental idiomatic Clojure library on top of the Riak Java client"
  :url "http://clojureriak.info"  
  :license {:name "Eclipse Public License"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure        "1.3.0"]
                 [com.basho.riak/riak-client "1.0.5"]
                 [org.clojure/data.json      "0.1.2"]
                 [clojurewerkz/support       "0.3.1"]
                 [com.novemberain/validateur "1.1.0"]]
  :source-paths ["src/clojure"]
  :profiles       {:1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
                   :1.5 {:dependencies [[org.clojure/clojure "1.5.0-master-SNAPSHOT"]]}
                   :dev {:resource-paths ["test/resources"]
                         :dependencies [[org.clojure/core.cache "0.5.0" :exclusions [org.clojure/clojure]]
                                        [ring/ring-core         "1.1.0"]]}}
  :mailing-list {:name "clojure-riak"
                 :archive "https://groups.google.com/group/clojure-riak"
                 :post "clojure-riak@googlegroups.com"}  
  :aliases        {"all" ["with-profile" "dev:dev,1.4:dev,1.5"]}
  :test-selectors {:focus   :focus
                   :2i      :2i
                   :cache   :cache
                   :mr      :mr
                   :links   :links
                   :search  :search
                   :default (constantly true)}
  :repositories   {"sonatype" {:url "http://oss.sonatype.org/content/repositories/releases"
                               :snapshots false
                               :releases {:checksum :fail :update :always}}
                   "sonatype-snapshots" {:url "http://oss.sonatype.org/content/repositories/snapshots"
                               :snapshots true
                               :releases {:checksum :fail :update :always}}}
  :warn-on-reflection true)
