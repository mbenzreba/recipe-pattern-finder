
# Recipe Pattern Finder

## Purpose

To analyze output produced by pre-trained Apache OpenNLP models, specifically the POSTagger (*Phrase-of-Speech*). 
Goal is to programmatically analyze this output to see if patterns can be found in how language is used in 
recipes, so that data extraction (e.g. measurements of ingredients, cooking steps, etc.) can be streamlined for 
the Houschef app.

## Plan of Attack

1. **Analyze phrases of speech within tokens to determine word relations common to cooking instructions.** From a quick
inspection I made of `peruvian-chicken.txt` I processed through `opennlp-basic-pipeline.ps1`, I found that some
instructions followed a similar pattern: at the start of the sentence, and before the first comma (i.e. the first token
of the sentence), there was always a _IN (preposition) POS, then 1 or 2 words, then a _NN POS (noun, singular or mass).
Take for example: For (IN) the marinade (NN). I cannot analyze and detect each and every pattern myself, so I will
write a tool to analyze and return common patterns found in the POS tagged corpora.

2. **Use patterns found to develop a "cleaning pipeline" by which I can clean the language used in recipes for easier parsing.**
This pipeline should be found in CustmParser and should be tweakable.

## Concerns

* OpenNLP does not seem to recognize the n with a tilde on top, found in ingredients like jalapenos