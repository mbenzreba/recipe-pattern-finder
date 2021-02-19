
# Recipe Pattern Finder

## Purpose

To analyze output produced by pre-trained Apache OpenNLP models, specifically the POSTagger (*Phrase-of-Speech*). 
Goal is to programmatically analyze this output to see if patterns can be found in how language is used in 
recipes, so that data extraction (e.g. measurements of ingredients, cooking steps, etc.) can be streamlined for 
the Houschef app.

## Concerns

* OpenNLP does not seem to recognize the n with a tilde on top, prevalent in ingredients like jalapenos