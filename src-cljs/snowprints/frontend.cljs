(ns snowprints.frontend
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<! >!]]
            [haslett.client :as ws]
            [reagent.core :as reagent]))

(go (let [stream (<! (ws/connect "ws://localhost:3000/loc/"))]
      (>! (:sink stream) "Hello World")
      (js/console.log (<! (:source stream)))
      (ws/close stream)))

(defonce state (reagent/atom {}))

(defn is-in? [an-item a-seq]
  (some #{an-item} a-seq))

(defn page []
  [:div "test test"])

(reagent/render-component [page] (.getElementById js/document "appdiv"))
