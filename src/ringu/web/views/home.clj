(ns ringu.web.views.home
  (:require [ringu.web.views.helpers :as helpers]))

(defn index-page [req]
  (tap> req)
  (helpers/layout req "Αρχική σελίδα"
                  [:hr [:a {:class "button primary"
                            :href (helpers/get-path :suppliers)} "Προμηθευτές"]
                   1 [:div (str (:flash req))] 1
                   [:div (str req)]]))