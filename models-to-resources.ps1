
<#
FILE        : models-to-resources.ps1
PROJECT     : recipe-pattern-finder
DEVELOPER   : Mohamed Benzreba
DESCRIPTION : Copies in models from .\models into the resources directories found in the actual
    java src. This is so that the main models can stay in the .\models directory and do not have
    to be duplicated across multiple directories when committing to repo.
#>