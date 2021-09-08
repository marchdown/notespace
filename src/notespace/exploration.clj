(ns notespace.exploration
  (:require  [midje.sweet :as midje]
             [notespace.view :as view]
             [notespace.note :as note]
             [notespace.api :as notespace]
             [notespace.kinds :as kind]
             ;;[clojure.data/csv :as csv]
             [notespace.setup-test]
             [midje.sweet :refer [with-state-changes => fact facts before]]
             [clojure.test :refer :all]))

(notespace/init :port 3333)
l(notespace/eval-this-notespace)
(notespace/eval-and-realize-this-notespace)

(notespace/listen)
(fact "map-of-seqs converts to grid"
      (->
       (view/dataset->grid-hiccup
        {:a [1 2 3]
         :b [4 5 6] })
       (nth 3)
       (second)
       :rowData) =>
      [{"a" 1 "b" 4}
       {"a" 2 "b" 5}
       {"a" 3 "b" 6}])

(fact "seq-of-maps converts to grid"
      (->
       (view/dataset->grid-hiccup
        [{:a 1 :b 4}
         {:a 2 :b 5}
         {:a 3 :b 6}])
       (nth 3)
       (second)
       :rowData) =>
      [{"a" 1 "b" 4}
       {"a" 2 "b" 5}
       {"a" 3 "b" 6}])

(def simple-note
  (first  (note/ns-notes "notespace.simple-notespace-test")))



(def testing-port 3091)
