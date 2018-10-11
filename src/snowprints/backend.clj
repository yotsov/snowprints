(ns snowprints.backend
  (:require [compojure.core :as c]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty9 :as jetty]
            [ring.adapter.jetty9.websocket :as ws])
  (:gen-class))

(defonce ws-handler
         {:on-connect (fn [ws] (ws/send! ws "hi!" {:write-success (fn [] (println "successfully sent message"))}))
          :on-text (fn [ws text-message] (println "Received: " text-message))})

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
  (reset! jetty-server
          (jetty/run-jetty app
                           {:port 3000
                            :join? join?
                            :websockets {"/loc" ws-handler}})))

(defn -main []
  (println "JAR application started.")
  (start-jetty true))
