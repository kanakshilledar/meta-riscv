DESCRIPTION = "Linux kernel for LicheePi 4A"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

SRC_URI = "git://github.com/esmil/linux.git;branch=main;protocol=https"
SRCREV = "221850f11052260cccf575e41ed6986af4a2ebe3"

KERNEL_DEFCONFIG = "th1520_defconfig"
COMPATIBLE_MACHINE = "(licheepi-4a)"