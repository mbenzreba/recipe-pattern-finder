
# The Models


### en-sent.bin

[*Downloaded from OpenNLP's page.*](http://opennlp.sourceforge.net/models-1.5/)

### en-token.bin

[*Downloaded from OpenNLP's page.*](http://opennlp.sourceforge.net/models-1.5/)

Does not seem to understand breaks in recipes. Will probably have to train a new tokenization model on 
how to break up sentences in recipes once I identify exactly how to break up the sentences to benefit 
the machine understanding of recipes better.

### en-pos-maxent.bin

[*Downloaded from OpenNLP's page.*](http://opennlp.sourceforge.net/models-1.5/)

When running against a single recipe (peruvian-chicken.txt), it produced some errors. Most notably, it seems
to think the term "whisk" is always a noun, whereas in recipe instructions the term is often used as a
verb. Another flaw is that when a recipe uses foreign ingredients, the POSTagger has an unpredictable way
of tagging. Further research will have to be done.