FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# Support fdt drivers for AE350
SRCREV:ae350-ax45mp = "22f38ee6c658a660083aa45c4ec6c72f66a17260"
SRCREV:jh7100 = "c6a092cd80112529cb2e92e180767ff5341b22a3"
SRCREV:jh7110 = "c6a092cd80112529cb2e92e180767ff5341b22a3"
SRCREV:milkv-duo = "v1.4"
SRCREV:licheepi-4a = "lpi4a"
# recent version after 1.4 release
SRCREV:c910 = "17e829129d60d7d178d47ecbd8990e705690d352"

SRC_URI:jh7110 = "git://github.com/starfive-tech/opensbi;branch=JH7110_VisionFive2_devel;protocol=https"
SRC_URI:append:jh7110 = "\
	file://visionfive2-uboot-fit-image.its \
	"

SRC_URI:milkv-duo = "git://github.com/riscv-software-src/opensbi.git;branch=master;protocol=https"

SRC_URI:licheepi-4a = " git://github.com/xhackerustc/thead-opensbi.git;branch=main;protocol=https"
PLATFORM:licheepi-4a = "generic"

DEPENDS:append:jh7110 = " u-boot-tools-native dtc-native"
EXTRA_OEMAKE:append:jh7110 = " FW_TEXT_START=0x40000000"

EXTRA_OEMAKE:append:milkv-duo = "FW_FDT_PATH=${DEPLOY_DIR_IMAGE}/u-boot.dtb"

_DEPS = ""
_DEPS:milkv-duo = "u-boot:do_deploy"

do_compile[depends] += "${_DEPS}"

do_deploy:append:jh7110() {
	install -m 0644 ${UNPACKDIR}/visionfive2-uboot-fit-image.its ${DEPLOYDIR}/visionfive2-uboot-fit-image.its
	cd ${DEPLOYDIR}
	mkimage -f visionfive2-uboot-fit-image.its -A riscv -O u-boot -T firmware visionfive2_fw_payload.img
}
