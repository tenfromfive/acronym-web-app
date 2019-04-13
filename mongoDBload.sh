#!/bin/bash
#NOTE: Run this Script to populate the JP Acronym and Definitions.

#install the Mongo service prior to running this script
#Mongo
mongo < mongoDBInjectScript.js
