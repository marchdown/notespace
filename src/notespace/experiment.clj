(ns notespace.experiment
  (:require [notespace.api :as notespace]
            [notespace.kinds :as kind]
            [clojure.data.csv :as csv]
            ;;[clojure.java.io :as io]
            [notespace.state :as state]
            [notespace.paths :as paths])

  (:use [amazonica.aws.ec2]))


;; (create-snapshot :volume-id   "vol-8a4857fa"
;;                  :description "my_new_snapshot")


^kind/hidden
(comment
  (notespace/init-with-browser)
  (notespace/init-with-browser :port 1927)

  (notespace/init)
  (notespace/init :port 1904)

  (notespace/stop-server)


  (notespace/eval-this-notespace)

  (notespace/eval-and-realize-this-notespace)

  (notespace/update-config #(assoc % :target-base-path "doc"))
  (notespace/render-static-html)

  (future
    (clojure.java.shell/sh
     "firefox"
     "doc/notespace/v3-experiment1-test/index.html"))

  (notespace/listen)

  (notespace/unlisten)

  (notespace/toggle-single-note-mode true)
  (notespace/toggle-single-note-mode false))

["# Notespace Workflow & Entry Points"]

["What does my environment look like about now: MacOS, Doom Emacs, notespace git repo, read-file emacs-profile.el, cider-jack-in" ]

["# Sessions"]

["## DB Session"]

["## AWS Session"]

;; ((describe-instances) :reservations)
;; (keys (describe-instances))
;; (values (describe-instances))
;; (describe-images)

["## TODO"]
["### Feature Engineering Session"]
["### Saving & Loading Features"]
["### Cortex"]
["### Scicloj I."]
["### Scicloj II."]
["### Vega"]


["# Exploration Intro

  A Literate Programming Excursion into Finance

  ## Order Flow

  The market is presented as a sequence of orders. The orders comprise *orderbook*. We are given orderbook snapshots as follows:


  ## Here they are as a text file:"]



;;(csv/parse-csv ("~/Downloads/snapshot.csv"))
  (def data (csv/read-csv
    (slurp
     "/Users/uprootiny/tardis_incremental_book_L2_super.csv")
    ))

(def fname "/Users/uprootiny/data/feature_df_Sept.csv")
(def learning-set (csv/read-csv (slurp fname)))

["### Parsed CSV"]

(def a_moment_in_time (take 2 (take 10 (list data))))

(count (first a_moment_in_time))





(count (map count (last (take 10 (list data)))))


^kind/dataset-grid
{:x (range 9)
 :y (repeatedly 9 rand)
 :z (take 9 (cycle "abc"))}

^kind/dataset-grid
data

["## Data is presented in several different formats

- csv files
- db queries
- s3 URIs
  - patterns thereof
  - filtering functions
"]


["That's our first look at the data. Let's expand on it.

Data is stored in *S3 Cloud Storage*. It should be accessible with the AWS credentials and a library."]

["Example data URI:" "s3://crypto-data-sources/tardis/data_type=oc_mid5_strike_by_rank/res=1m"]


;;n)


["

[Notespace](https://github.com/scicloj/notespace) is a library that turns any Clojure namespace into a 'notespace' -- a namespace with a [notebook](https://en.wikipedia.org/wiki/Notebook_interface) experience. A notespace evolves in an interactive process of exploration, involving a dialogue of your editor, your REPL, your browser and your mind.

So, `Notespace` with a capital `N` `W` is the name of the tool, and `notespace` with a small `n` is the notion that we just presented.

Here we describe an experimental incomplete draft of Notespace Version 3.

It is still a bit buggy around handling concurrency, updating efficiently and connecting to the browser. But the current version should be enough to start a community discussion around where we should be taking this."]

["## Notes

Here is a note:"]

(+ 1 31
   3 4
   5 8)
;;(csv/csv-reader (slurp "~/Downloads/snapshot.csv"))

["Notespace renders it so that we see both the code and the evaluation result.

All your namespace is seen as a sequence of toplevel forms, which are considered as notes, and rendered accordingly."]

["## Basic workflow

Typically, you may like to edit one or more namespaces, that may be about function definitions, tests, data exploration, etc., and have Notespace update its notebook view while these namespaces evolve. At some point, you may like to render the view as a standalone html file for documentation.

See [this screencast](https://www.youtube.com/watch?v=_GcTnkhn9g0) for a recorded session. Note, however, that the note kinds defined in the screencast are a bit different from those of the current version."

"### The main API functions

Let us see how to get along with notespaces using the Notespace API."

"#### init

Use `notespace.api/init` to initialize the Notespace system's state.

In the beginning of your work, you need to use it.

You can also use it afterwards. That is useful if you run into an unstable state (yes, this may happen sometimes -- there may be some concurrency bugs)."

"#### init-with-browser

Use `notespace.api/init-with-browser` to initialize the Notespace system's state and open a browser tab with a notebook view of the last namespace touched.

In the beginning of your work, you can use it instead of `init`.

You can always refersh the browser tab. That is useful if it runs out of sync with the system's state (yes, this may happen sometimes -- there may be some concurrency bugs)."

"#### eval-this-notespace

Use `notespace.api/eval-this-notespace` to evaluate the current namespace, one note after another, and inform Notespace about the evaluation results, one after another.
"]

["#Hello!"]

(+ 2 4 5 9 11 44)

(csv/parse-csv (slurp (clojure.java.io/file "/Users/uprootiny/tardis_incremental_book_L2_super.csv")))

(* 3 4 5)

["
If you have a browser view open, it should show the updated rendering of the state of the notespace last touched."

"#### eval-note-at-line *** ***

"]
["## *!*"]
(notespace/eval-note-at-line 120)

["

Use `notespace.api/eval-note-at-line` to evaluate the note at a certain line and inform Notespace about the evaluation result.

For example, `(notespace.api/eval-note-at-line 14)` applies that to the note at line 14.

If you have a browser view open, it should show the updated rendering of the state of the notespace last touched.

This is useful for re-evaluating parts of the notebook, whose evaluation result may have changed for some reason."

"#### render-static-html

Use `notespace.api/render-static-html` to render the Notespace state of the notespace last touched as a standalone html file. This html should look exactly as the dynamic browser view at the moment of rendering."]

["### Editor integration

For a convenient interactive experience, it is ecommended to bind the Notespace API functions to functions of your editor.

You can see how to do this in Emacs in [this Elisp code](https://github.com/scicloj/notespace/blob/v3/emacs-config.el). In the future, we will probably provide similar solutions for other editors too."]

["## References

Clojure references such as delays, futures, promises and atoms are handled by Notespace in a special way. Below we will see how Notespace offers ways to bring the dynamic REPL experience of such constructs to the dynamic browser view.

### The rule of rendering

Assume that a note's evaluation result `x` is a reference.

If `x` is already realized, then Notespace renders `@x` (dereferenced `x`) instead of `x`. In the rendering value, this is marked by `(dereferenced)`.

Otherwise, you should see a mark that says it is still pending.


For example:"]

(def d1 (delay 11))
(def d2 (delay 12))
(deref d2)
d1
d2

["### More API functions

Notespace offers some more API functions that allow for a dynamic experience with Clojure references."]

["#### eval-and-realize-this-notespace

Use `notespace.api/eval-and-realize-this-notespace` to evaluate all notes in the current namespace sequentially, one note after another, and then realize them in the background. As always, Notespace is informed about the results."]

["#### eval-and-realize-note-at-line

Use `notespace.api/eval-and-realize-note-at-line` to evaluate the note at a certain line, realize the value if it is an unrealized derefable value, and inform Notespace about the result.

For example, `(notespace.api/eval-and-realize-note-at-line 14)` applies that to the note at line 14."]

["#### eval-and-realize-notes-from-line

Use `notespace.api/eval-and-realize-notes-from-line` to evaluate and realize all notes starting from a certain line, and inform Notespace."]

["#### eval-and-realize-notes-from-change

 Use `notespace.api/eval-and-realize-notes-from-change` to evaluate and realize all notes starting from the line where the file has changed in its last save, compared to the previous save."]

["### Listening to changes

`(notespace.api/listen)` will tell Notespace to listen to changes in the file corresponding to the current namespace (on a file save). When changes occur, (notespace.api/eval-and-realize-notes-from-change) is called. This results in an [Oz](https://github.com/metasoarous/oz)-like live-reload experience.

`(notespace.api/unlisten)` will tell Notespace to stop listening to the current namespace."]


["### Delays"]

["If a note's value is a delay, then you can call `notespace.api/eval-and-realize-note-at-line` or `notespace.api/eval-and-realize-this-notespace` to realize it. Notespace will update its state with the dereferenced value.

For example:"]

(delay (+ 1 2))

["If you have called `notespace.api/eval-and-realize-note-at-line` with the line holding this note, or `notespace.api/eval-and-realize-this-notespace` with this notespace, then you should see the number 3 there. Otherwise, you should see a mark that says it is still pending."]

["### Futures"]

["If a note's value is a future, then Notespace will be informed when that future is realized. This allows Notespace to update its state with the dereferenced value.

For example, the following note should render after two seconds (of waiting for `x` to be realized)." ]

(future
  (Thread/sleep 2000)
  (->> #(- (rand) 0.5)
       (repeatedly 9)
       (reductions +)))

["### Atoms"]

["If a note's value is an atom, then Notespace will be informed when its value changes. This allows Notespace to update its state with the dereferenced value.

For example:"]

(def a
  (atom {:x 3}))

a

["If you evaluated the code in the comment below once, then you should see `{:x 4}`. Otherwise, you should still see `{:x 3}`."]

^kind/void
(comment
  (swap! a update :x inc))

["### Promises
Coming soon."]

["## Note kinds

Each note has a kind, that determines its rendering behaviour.

One way to specify a note's kind is by metadata. For example, here is a note of kind `:hiccup`."]

(require '[notespace.kinds :as k])

^kind/hiccup
[:big [:big 3]]

["Let us see what kinds of notes we have.

### naive

A `:naive` note renders its value by pretty-printing it."]

^kind/naive
(->> (partial rand-int 6)
     (partial repeatedly 6)
     (repeatedly 6))

["This is the default kind, so usually there is no need to mention it."]

["### void

A `:void` note does not render its value."]

^kind/void
(+ 1 2)

["### hidden

A `:hidden` note does not render its value, and also does not show its code.

Here is a note that you cannot see:"]

^kind/hidden
(+ 1 2)

["### md

Notes of kind `:md` are rendering their values as markdown. More precisely, the value is printed, and the captured printed string is expected to be of Markdown format. If the value is sequential, then its elements are printed and concantenated as lines of one multi-line string, that is then treated as Markdown."]

^kind/md
["##### text:"
 "* **bold**
* *italic*
* [link](https://www.youtube.com/watch?v=oIw7E-q-flc)"]

^kind/md
(cons
 "##### numbers:"
 (for [i (range 1 4)]
   (apply str "* " (repeat i i))))

["### md-nocode

Notes of kind `:md-nocode` are rendered as Markdown too, but without showing the code. Here is an example:"]

^kind/md-nocode
(->> "abcd"
     (map (fn [ch]
            (str "* " ch))))

["### hiccup

A `:hiccup` note renders its value as Hiccup."]

^kind/hiccup
[:big [:big 3]]

["### hiccup-nocode

Notes of kind `:hiccup-nocode` are rendered as Hiccup too, but without showing the code. Here is an example:"]

^kind/hiccup-nocode
[:big [:big 3]]

["### extended hiccup

In fact, `:hiccup` and `:hiccup-nocode` render by an extended version of Hiccup, that supports [gorilla-ui](https://github.com/pink-gorilla/gorilla-ui) tags.

Here are some examples."]

^kind/hiccup
[:div
 [:h5 "Plot:"]
 [:p/sparklinespot
  {:data      (->> #(- (rand) 0.5)
                   (repeatedly 99)
                   (reductions +))
   :svgHeight 20}]]

^kind/hiccup
(into [:ul]
      (for [i (range 9)]
        [:li
         i " "
         [:p/sparklinespot
          {:data      (for [j (range 999)]
                        (+ (* 0.2 (rand))
                           (Math/sin (* i j))))
           :limit     100
           :svgWidth  100
           :svgHeight 20}]]))

^kind/hiccup
[:div
 [:h5 "Plot:"]
 [:p/sparklinespot
  {:data      (->> #(- (rand) 0.5)
                   (repeatedly 99)
                   (reductions +))
   :svgHeight 20}]]

^kind/hiccup
(into [:ul]
      (for [i (range 9)]
        [:li
         i " "
         [:p/sparklinespot
          {:data      (for [j (range 999)]
                        (+ (* 0.4 (rand))
                           (Math/sin (* i j))))
           :limit     100
           :color     'red'
           :svgWidth  200
           :svgHeight 30}]]))


(require '[gorilla-notes.components.leaflet.providers :as leaflet-providers])

^kind/hiccup
[:p/leafletmap
    {:tile-layer leaflet-providers/Stamen-TonerLite}
    [{:type   :view
      :center [51.49, -0.08]
      :zoom   12
      :height 600
      :width  700}
     {:type   :rectangle
      :bounds [[51.49, -0.08]
               [51.5, -0.06]]}
     {:type      :circle
      :center    [51.505, -0.09]
      :fillColor :blue
      :radius    200}
     {:type      :polygon
      :positions [[51.515, -0.09]
                  [51.52, -0.1]
                  [51.52, -0.12]]
      :color     :purple}
     {:type      :polygon
      :positions [[[51.51, -0.12]
                   [51.51, -0.13]
                   [51.53, -0.13]]
                  [[51.51, -0.05]
                   [51.51, -0.07]
                   [51.53, -0.07]]]
      :color     :purple}
     {:type      :line
      :positions [[51.505, -0.09]
                  [51.51, -0.1]
                  [51.51, -0.12]]
      :color     :lime}
     {:type      :line
      :positions [[[51.5, -0.1]
                   [51.5, -0.12]
                   [51.52, -0.12]]
                  [[51.5, -0.05]
                   [51.5, -0.06]
                   [51.52, -0.06]]]
      :color     :lime}
     {:type     :marker
      :position [51.505, -0.09]}
     {:type     :marker
      :position [51.51, -0.12]
      :popup    "wow"}
     {:type      :circlemarker
      :center    [51.52, -0.06]
      :fillColor :blue
      :radius    200
      :popup     "square the circle"}]]

;; ^kind/hiccup
;; [:p/player
;;  {:width  "100%"
;;   :height "100%"
;;   :url    "https://www.youtube.com/watch?v=G512fvK9KXA"}]

["### sci

We can use the [Small Clojure Interpreter](https://github.com/borkdude/sci) to run code on the browser side:"]

^kind/hiccup
[:p/sci '(+ 1 2)]

^kind/sci
[:div [:big[:big '(+ 1 2)]]]

["### Quil (experimental)"]

["We can create [Quil](https://github.com/quil/quil) components. This is still work in progress."]

["The following example is an adaptation of [quil-reagent-demo](https://github.com/yogthos/quil-reagent-demo)."]

^kind/quil
{:draw         '(fn [{:keys [circles]}]
                 (q/background 255)
                 (doseq [{[x y] :pos [r g b] :color} circles]
                   (q/fill r g b)
                   (q/ellipse x y 10 10)))
 :update-state '(fn [{:keys [width height] :as state}]
                 (update state :circles conj {:pos   [(+ 20 (rand-int (- width 40)))
                                                      (+ 20 (rand-int (- height 40)))]
                                              :color (repeatedly 3 #(rand-int 250))}))
 :init         '(fn [width height]
                 (fn []
                   {:width   width
                    :height  height
                    :circles []}))}

["### vega"]

["The `vega` kind supports both Vega and Vega-Lite visualizations."]

^kind/vega
{:description "A simple bar chart with embedded data."
 :data        {:values [{:a "A" :b 28} {:a "B" :b 55} {:a "C" :b 43}
                        {:a "D" :b 91} {:a "E" :b 81} {:a "F" :b 53}
                        {:a "G" :b 19} {:a "H" :b 87} {:a "I" :b 52}]}
 :mark        :bar
 :encoding    {:x {:field :a :type :nominal :axis {:labelAngle 0}}
               :y {:field :b :type :quantitative}}}

["### code"]

["Some additional tags such as `:p/code` are added by the [gorilla-notes](https://github.com/scicloj/gorilla-notes) infrastructure."]

^kind/hiccup
[:p/code "(defn abcd [x] (+ x 9))"]

^kind/hiccup
[:p/code "def abcd(x):
  return x+9"
 {:language :python}]

["Such tags can usually be handled through dedicated kinds too."]

^kind/code
["(defn abcd [x] (+ x 9))"
 "(defn abcd [x] (+ x 3))"]

^kind/code
(pr-str '(defn abcd [x] (+ x 9)))

["### math"]

["Regular markdown text can contain math expressions using TeX notation: $(ax ^2 + bx + c = 0 )$."]

["We can also include math tags in Hiccup elements:"]

^kind/hiccup
[:p/math
 "(ax ^2 + bx + c = 0 )"]

["We can also have notes of math kind:"]

^kind/math
["(ax ^2 + bx + c = 0 )"
 "(dx ^2 + ex + f = 0 )"]

^kind/math
(str "(ax ^2 "
     "+ bx "
     "+ c "
     " = 0)")

["### dataset

Notes of kind `:dataset` are expecting a dataset that prints nicely as a table in markdown (for exaple, a dataset of the [tech.ml.dataset](github.com/techascent/tech.ml.dataset) and [Tablecloth](https://github.com/scicloj/tablecloth) libraries).

It wraps the resulting markdown with nice Bootstrap CSS classes, so that the table is displayed nicely.

We do not have an example here, since that would require depending on the above libraries."]

["### dataset-grid

Notes of kind `:dataset-grid` are expecting a value which is one of the following:
- a map, whose values are same-length sequentials (one example of such a map is a dataset of the [tech.ml.dataset](github.com/techascent/tech.ml.dataset) and [Tablecloth](https://github.com/scicloj/tablecloth) libraries);
- a sequence of maps.

They render that values as an [ag-Grid](https://www.ag-grid.com/) table view.

For example:"]

^kind/dataset-grid
{:x (range 9)
 :y (repeatedly 9 rand)
 :z (take 9 (cycle "abc"))}

["--------------------"]

^kind/dataset-grid
{:a [1 2 3]
 :b [4 5 6]}

["--------------------"]

^kind/dataset-grid
[{:a 1 :b 4}
 {:a 2 :b 5}
 {:a 3 :b 6}]

["--------------------"]

["### Plain html"]

^kind/html
(str "<h4>h4</h4>"
     "<h5>h5</h5>")

;; ["## Other ways to specify note kinds

;; A note's kinds can also be specified by including it in a vector beginning with a keyword. This is inspired by the approach of [Oz](https://github.com/metasoarous/oz), and should allow some partial compatibility with Oz notebooks."]

;; [:hiccup [:big [:big 3]]]

["Notes which are sequential forms beginning with a string (e.g., `[\"Hello!\" \"How are you?\"]`) are assigned the default kind `:md-nocode`  automatically."
 "This very text you are reading is an example of a note of that kind."]

["Notes which are forms like `(def ...)` or `(defn ...)` are assigned the kind `:void`."]

["All other notes witn no explicit metadata are assigned the kind `:naive` automatically."]

(+ 1 2)

["## Interactive input and reactive notes

Coming soon."]

;; ^kind/hiccup
;; [:p/slider :x {:min 0 :max 100 :initial-value 0}]

;; ^kind/void
;; (require '[notespace.api :refer [R]])

;; (R [x]
;;    (* x 100))

["## Progress logging

This should be documented better soon."]

(comment
  (require '[notespace.cljfx.api :as fx]) 

  (notespace/update-config #(assoc % :evaluation-callback-fn
                             (fn [idx note-count note]
                               (let [expected-duration (or  (get-in note [:duration]) 0)]
                                 (println "evaluate note: " idx "/" (dec note-count)
                                          ", expected duration (s): "
                                          (if (pos? expected-duration)
                                            expected-duration
                                            "???"))))))

  (notespace/update-config #(assoc % :in-eval-count-down-fn
                           (fn [seconds]
                             (println seconds))))
  (reset! state/the-context
          (fx/swap-context @state/the-context
                           #(-> % (assoc-in [:config :render-src?] false)))))

["## Overriding note kinds at runtime"

 "It is possible to override a note's kind during its evaluation using the `notespace.kinds/override` functions."]

(-> [:big [:big 3]]
    (kind/override kind/hiccup))

(-> ["(ax ^2 + bx + c = 0 )"]
    (kind/override kind/math))

(-> {:description "A simple bar chart with embedded data."
     :data        {:values [
                            {:a "A" :b 28} {:a "B" :b 55} {:a "C" :b 43}
                            {:a "D" :b 91} {:a "E" :b 81} {:a "F" :b 53}
                            {:a "G" :b 19} {:a "H" :b 87} {:a "I" :b 52}
                            ]}
     :mark        :bar
     :encoding    {:x {:field :a :type :nominal :axis {:labelAngle 0}}
                   :y {:field :b :type :quantitative}}}
    (kind/override kind/vega))

["## Inferring note behaviors from types"

 "It is possible to infer a note's behavior from the type of its rendered, overriding the behavior prescribed by its kind. This can be done by extending the `Behaving` protocol."]

(require '[notespace.behavior])

(deftype DummyType1 [data]
  notespace.behavior/Behaving
  (->behavior [this]
    {:render-src? true
     :value->hiccup (fn [v] data)}))

(DummyType1. [:big [:big 3]])

["## Extending Notespace with new note kinds."]

(defmethod
  ^{:doc
    "Example of user extensible kind.
Note the ':reload true' in the :require vector:
[notespace.kinds :as k :reload true].
This will make REPL testing your extensible kind easier!"}
  kind/kind->behaviour ::just-code
  [_]
  {:render-src?   true
   :value->hiccup (constantly nil)})

(def ^{:doc "When making a user extensible kind, you MUST assign the keyword
to a variable. Annotating with a keyword WILL NOT WORK!"} just-code ::just-code)

["An example of properly annotated form with a variable that resolves to a keyword which is attached to a kind->behavior method:"]

^just-code
[:div "just some code"]

["## Tests"]

["### clojure.test"]

(require '[clojure.test :refer [deftest testing is are]])

(deftest t1
  (testing "abcd"
    (is (= 3 3))))

["We can ask for the summary of all clojure.test tests:"]

(notespace/clojure-tests-summary)

["### Midje

Tests of the Midje test framework are rendered as follows:"]

(require '[midje.sweet :refer [fact]])

(fact
 (+ 1 2) => 3)

["We can ask for the summary of all midje tests:"]

(notespace/midje-summary)

["### total"]

["We can ask for the summary of all tests:"]

(notespace/tests-summary)


["## External files (experimental)

Support for external files is still considered experimental. The API will stabilize soon."]

["To put files under your local project directory, in a place that is accessible to notespace, please use the `notespace.api/file-target-path` function."]

(notespace/file-target-path "dummy.txt")

["Let us put some files there."]

^kind/void
(spit (notespace/file-target-path "dummy.txt")
      "hi!")

(require '[clojure.java.io :as io])

;; https://stackoverflow.com/a/19297746
(defn copy [uri file]
  (with-open [in  (io/input-stream uri)
              out (io/output-stream file)]
    (io/copy in out)))

^kind/void
(copy "https://clojure.org/images/clojure-logo-120b.png"
      (notespace/file-target-path "clojure.png"))

["Now we can include file links and images as follows:"]

^kind/hiccup
(notespace/file-link-tag "download dummy.txt" "dummy.txt")

^kind/hiccup
[:button (notespace/file-link-tag "download dummy.txt" "dummy.txt")]

(notespace/img-file-tag "clojure.png" {})

["When rendering to static HTML, the external files will be moved alongside it, so that the same links work."]
