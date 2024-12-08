DESCRIPTION = "Genimage configuration for the TH1520 board"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835a8ab65e2278c60bf16d0cd760c94"

inherit image

SRC_URI = "file://genimage.cfg \
           file://th1520-boot-firmware.tar.gz \
           file://extlinux/extlinux.conf"

# S = "${WORKDIR}"

# Define temporary paths
ROOTPATH_TMP = "${WORKDIR}/rootpath-tmp"
GENIMAGE_TMP = "${WORKDIR}/genimage-tmp"

do_unpack() {
    tar -xzf ${WORKDIR}/th1520-boot-firmware.tar.gz -C ${WORKDIR}/th1520-boot-firmware
}

do_configure[noexec] = "1"

do_compile[noexec] = "1"

do_install[noexec] = "1"

do_image() {
    set -e

    # Create temporary directories
    mkdir -p "${ROOTPATH_TMP}" "${GENIMAGE_TMP}"

    # Copy files to temporary rootpath
    cp ${DEPLOY_DIR_IMAGE}/Image ${DEPLOY_DIR_IMAGE}/*.dtb ${DEPLOY_DIR_IMAGE}/fw_dynamic.bin "${ROOTPATH_TMP}"
    cp -a "${WORKDIR}/th1520-boot-firmware/addons/boot/"* "${ROOTPATH_TMP}"
    cp -a "${WORKDIR}/extlinux/" "${ROOTPATH_TMP}"

    # Run genimage
    genimage \
        --rootpath "${ROOTPATH_TMP}" \
        --tmppath "${GENIMAGE_TMP}" \
        --inputpath "${DEPLOY_DIR_IMAGE}" \
        --outputpath "${DEPLOY_DIR_IMAGE}" \
        --config "${WORKDIR}/genimage.cfg"

    # Clean up temporary directories
    rm -rf "${ROOTPATH_TMP}" "${GENIMAGE_TMP}"
}

addtask do_image after do_rootfs before do_build

COMPATIBLE_MACHINE = "(licheepi-4a)"
