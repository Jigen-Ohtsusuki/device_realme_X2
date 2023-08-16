#
# Copyright (C) 2022 The droidxOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/product_launched_with_p.mk)

# Inherit some common droidx stuff.
$(call inherit-product, vendor/droidx/config/common_full_phone.mk)

# Inherit from X2 device
$(call inherit-product, device/realme/X2/device.mk)

# Device identifier. This must come after all inclusions.
PRODUCT_NAME := droidx_X2
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
EXTRA_UDFPS_ANIMATIONS := true

# DroidX UI
DROIDX_GAPPS := true

BUILD_FINGERPRINT := "google/cheetah/cheetah:13/TQ3A.230705.001/10216780:user/release-keys"

PRODUCT_GMS_CLIENTID_BASE := android-oppo
