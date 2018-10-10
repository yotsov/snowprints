(ns snowprints.frontend
  (:require [reagent.core :as reagent]))

(defonce state (reagent/atom {}))

(defn is-in? [an-item a-seq]
  (some #{an-item} a-seq))

(defn page []
  [:div "test test"])

(reagent/render-component [page] (.getElementById js/document "appdiv"))
