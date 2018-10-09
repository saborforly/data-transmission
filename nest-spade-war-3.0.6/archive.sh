#!/bin/bash
# A simple script to create a tarball for distribution

if [ "X" = "X${1}" ] ; then
    echo usage: ${0} version [tag]
    exit 1
fi
SPADE_VERSION=${1}
if [ "X" = "X${2}" ] ; then
    SPADE_TAG=${SPADE_VERSION}
else
    SPADE_TAG=${2}
fi

git archive --format=tar --prefix=nest-spade-war-${SPADE_VERSION}/ ${SPADE_TAG} | gzip > nest-spade-war-${SPADE_VERSION}.tgz
