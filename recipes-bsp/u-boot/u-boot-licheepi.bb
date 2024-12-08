# Require U-Boot common configurations
require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

SUMMARY = "U-Boot for LicheePi 4A"
DESCRIPTION = "U-Boot bootloader for the LicheePi 4A RISC-V board"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRC_URI = "git://github.com/revyos/thead-u-boot.git;branch=th1520;protocol=https"
SRCREV = "fc9575fa63442ae87ced0b147ca1a14e30fd3d3d"
PV = "2023.04+git"

S = "${WORKDIR}/git"

DEPENDS:append = " u-boot-tools-native bison-native"

# Specify binary and symlink names
UBOOT_BINARY = "u-boot-with-spl.bin"
UBOOT_SYMLINK = "u-boot.bin"

# Define machine compatibility
COMPATIBLE_MACHINE = "(licheepi-4a)"
