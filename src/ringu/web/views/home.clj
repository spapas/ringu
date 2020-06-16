(ns ringu.web.views.home
  (:require [ringu.web.views.helpers :as helpers]))

(defn index-page [req]
  (helpers/layout "Αρχική σελίδα"
                  [:hr [:a {:class "button primary"
                            :href (helpers/get-path :suppliers)} "Προμηθευτές"]
                   [:div (str req)]]))