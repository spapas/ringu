(ns ringu.web.views.home
  (:require [ringu.web.views.helpers :as helpers]))

(defn index-page [req]
  (tap> req)
  (helpers/layout req "Αρχική σελίδα"
                  [:hr [:a {:class "button primary"
                            :href (helpers/get-path :suppliers)} "Προμηθευτές"]
                   [:div (str req)]]))