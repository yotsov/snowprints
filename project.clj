(defproject org.yotsov/snowprints ""
  ; lein -v -> Leiningen 2.8.1 on Java 1.8.0_51 Java HotSpot(TM) 64-Bit Server VM
  :dependencies [; backend
                 [org.clojure/clojure "1.9.0"]
                 [compojure "1.6.1"]
                 [info.sunng/ring-jetty9-adapter "0.12.0"]

                 ; frontend
                 [org.clojure/clojurescript "1.10.339"]
                 [reagent "0.8.1"]
                 [haslett "0.1.2"]
                 [figwheel "0.5.16"]]

  :plugins [[lein-figwheel "0.5.16"]
            [lein-cljsbuild "1.1.7"]]

  :cljsbuild {:builds [{:id "dev"
                        ; rm -rf resources/public/js/compiled; lein trampoline figwheel dev
                        :source-paths ["src-cljs/"]
                        :figwheel true
                        :compiler {:optimizations :none
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :main snowprints.frontend}}

                       {:id "prod"
                        ; rm -rf resources/public/js/compiled; lein cljsbuild once prod
                        :source-paths ["src-cljs/"]
                        :figwheel false
                        :compiler {:optimizations :advanced
                                   :output-to "resources/public/js/compiled/app.js"}}]}

  :aot :all
  :main snowprints.backend
  ; dev  -> rm -rf target; (echo "(start-jetty false)"; cat <&0) | lein repl
  ; prod -> rm -rf target; lein uberjar; java -Xms1G -Xmx1G -jar target/snowprints--standalone.jar
)
