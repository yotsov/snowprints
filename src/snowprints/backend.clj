(ns snowprints.backend
  (:require [compojure.core :as c]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty])
  (:gen-class))

(c/defroutes app-routes
             (c/GET "/" [] "<h1>Hello World</h1>"))

(def app
  (handler/site app-routes))

(defn start-app []
  (jetty/run-jetty app {:port 3000}))

(defn -main []
  (println "JAR application started.")
  (start-app))
