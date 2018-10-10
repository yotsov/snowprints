(defproject org.yotsov/snowprints ""
  ; lein -v -> Leiningen 2.8.1 on Java 1.8.0_51 Java HotSpot(TM) 64-Bit Server VM
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :aot :all
  :main snowprints.backend
  ; rm -rf target; lein uberjar; java -Xms1G -Xmx1G -jar target/snowprints--standalone.jar
  ; rm -rf target; lein repl
)
