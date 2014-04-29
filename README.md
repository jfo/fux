# Fux

This is a music project named for the pedagogical father of counterpoint: Johann Fux. It is a parser and player for Humdrum, written in Clojure.

Humdrum is a text based syntax for representing musical information designed to be easily parsed by bash scripts.

[http://www.musiccog.ohio-state.edu/Humdrum/](http://www.musiccog.ohio-state.edu/Humdrum/)

Fux turns these files into navigable data structures and then schedules synthesizers to produce output that reproduces the score. It relies on the Overtone library to communicate with the Supercollider software synth:

[http://overtone.github.io/](http://overtone.github.io/)
