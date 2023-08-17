#
# Copyright (C) 2022 The lineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/product_launched_with_p.mk)

# Inherit some common lineage stuff.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

# Inherit from X2 device
$(call inherit-product, device/realme/X2/device.mk)

# Device identifier. This must come after all inclusions.
PRODUCT_NAME := lineage_X2
PRODUCT_DEVICE := X2
PRODUCT_BRAND := realme
PRODUCT_MODEL := realme X2
PRODUCT_MANUFACTURER := realme

# Bootanimation Res.
TARGET_BOOT_ANIMATION_RES := 1080

# Some Common Rom features
TARGET_SUPPORTS_QUICK_TAP := true

# TARGET_HAS_UDFPS := true
TARGET_FACE_UNLOCK_SUPPORTED := true

# UDFPS Animations
#EXTRA_UDFPS_ANIMATIONS := true

# Inherit some RisingOSS stuff
RISING_CHIPSET := "Snapdragon 730G"
RISING_MAINTAINER := "Levi Ackerman"
TARGET_BUILD_APERTURE_CAMERA := true
TARGET_HAS_UDFPS := true
TARGET_USE_PIXEL_FINGERPRINT := true
WITH_GMS := true
TARGET_CORE_GMS := true
TARGET_USE_GOOGLE_TELEPHONY := false
TARGET_CORE_GMS_EXTRAS := true

BUILD_FINGERPRINT := "google/cheetah/cheetah:13/TQ3A.230705.001/10216780:user/release-keys"

PRODUCT_GMS_CLIENTID_BASE := android-oppo
