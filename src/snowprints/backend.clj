(ns snowprints.backend
  (:require [compojure.core :as c]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty])
  (:gen-class))

(c/defroutes app-routes
             (c/GET "/" [] (slurp "resources/public/index.html"))
             (route/resources "/"))

(defonce app
         (handler/site app-routes))

(defonce jetty-server (atom nil))

(defn stop-jetty []
  (when-let [jetty @jetty-server]
    (.stop jetty)
    (reset! jetty-server nil)))

(defn start-jetty [join?]
  (stop-jetty)
  (reset! jetty-server (jetty/run-jetty app {:port 3000 :join? join?})))

(defn -main []
  (println "JAR application started.")
  (start-jetty true))
