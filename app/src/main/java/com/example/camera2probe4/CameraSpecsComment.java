package com.example.camera2probe4;

import androidx.core.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_AUTO;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_CLOUDY_DAYLIGHT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_DAYLIGHT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_FLUORESCENT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_INCANDESCENT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_OFF;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_SHADE;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_TWILIGHT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_WARM_FLUORESCENT;
import static android.hardware.camera2.CameraMetadata.INFO_SUPPORTED_HARDWARE_LEVEL_3;
import static android.hardware.camera2.CameraMetadata.INFO_SUPPORTED_HARDWARE_LEVEL_EXTERNAL;
import static android.hardware.camera2.CameraMetadata.INFO_SUPPORTED_HARDWARE_LEVEL_FULL;
import static android.hardware.camera2.CameraMetadata.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY;
import static android.hardware.camera2.CameraMetadata.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_MANUAL_POST_PROCESSING;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_RAW;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_PRIVATE_REPROCESSING;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_READ_SENSOR_SETTINGS;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_BURST_CAPTURE;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_YUV_REPROCESSING;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_DEPTH_OUTPUT;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_CONSTRAINED_HIGH_SPEED_VIDEO;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_MOTION_TRACKING;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_LOGICAL_MULTI_CAMERA;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_MONOCHROME;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_SECURE_IMAGE_DATA;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_SYSTEM_CAMERA;
import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_OFFLINE_PROCESSING;
// API 31 import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_ULTRA_HIGH_RESOLUTION_SENSOR;
// API 31 import static android.hardware.camera2.CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_REMOSAIC_REPROCESSING;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_OFF;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_ON;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_ON_ALWAYS_FLASH;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_ON_AUTO_FLASH;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_ON_AUTO_FLASH_REDEYE;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_ON_EXTERNAL_FLASH;
import static android.hardware.camera2.CameraMetadata.CONTROL_AF_MODE_AUTO;
import static android.hardware.camera2.CameraMetadata.CONTROL_AF_MODE_CONTINUOUS_PICTURE;
import static android.hardware.camera2.CameraMetadata.CONTROL_AF_MODE_CONTINUOUS_VIDEO;
import static android.hardware.camera2.CameraMetadata.CONTROL_AF_MODE_EDOF;
import static android.hardware.camera2.CameraMetadata.CONTROL_AF_MODE_MACRO;
import static android.hardware.camera2.CameraMetadata.CONTROL_AF_MODE_OFF;


//@RequiresApi(api = Build.VERSION_CODES.Q) // API 29, Android 10
public class CameraSpecsComment {

    private static final List<Pair<Integer, String>> InfoSupportedHardwareLevel = Arrays.asList(
        new Pair<>(INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED, "LIMITED"),
        new Pair<>(INFO_SUPPORTED_HARDWARE_LEVEL_FULL, "FULL"),
        new Pair<>(INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY, "LEGACY"),
        new Pair<>(INFO_SUPPORTED_HARDWARE_LEVEL_3, "LEVEL_3"),
        new Pair<>(INFO_SUPPORTED_HARDWARE_LEVEL_EXTERNAL, "EXTERNAL")
    );

    private static final List<Pair<Integer, String>> RequestAvailableCapabilities = Arrays.asList(
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE, "Camera API Compatible"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR, "Manually Controlled"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_MANUAL_POST_PROCESSING, "Manually Controlled Postprocessing"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_RAW, "Outputting RAW Buffers"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_PRIVATE_REPROCESSING, "Zero Shutter Lag Reprocessing"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_READ_SENSOR_SETTINGS, "Accurately Reporting Sensor Settings"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_BURST_CAPTURE, "Burst Capture"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_YUV_REPROCESSING, "YUV_420_888 Reprocessing"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_DEPTH_OUTPUT, "Depth Measurements"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_CONSTRAINED_HIGH_SPEED_VIDEO, "High Speed Video Recording"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_MOTION_TRACKING, "Motion Tracking"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_LOGICAL_MULTI_CAMERA, "Multiplex Physical Cameras"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_MONOCHROME, "Monochrome Camera"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_SECURE_IMAGE_DATA, "Trusted Execution Environments Only"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_SYSTEM_CAMERA, "Permission SYSTEM_CAMERA"),
        new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_OFFLINE_PROCESSING, "Supports Offline Processing")
        // API 31, Android 12, Build.VERSION_CODES.S
        // new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_ULTRA_HIGH_RESOLUTION_SENSOR, "Ultra High Resolution"),
        // new Pair<>(REQUEST_AVAILABLE_CAPABILITIES_REMOSAIC_REPROCESSING, "Bayer Pattern Reprocessing")
    );

    private static final List<Pair<Integer, String>> ControlAwbMode = Arrays.asList(
        new Pair<>(CONTROL_AWB_MODE_AUTO, "AWB Active"),
        new Pair<>(CONTROL_AWB_MODE_OFF, "AWB Disabled"),
        new Pair<>(CONTROL_AWB_MODE_DAYLIGHT, "AWB Disabled (Daylight light: CIE D65)"),
        new Pair<>(CONTROL_AWB_MODE_CLOUDY_DAYLIGHT, "AWB Disabled (Cloudy daylight light)"),
        new Pair<>(CONTROL_AWB_MODE_TWILIGHT, "AWB Disabled (Twilight light)"),
        new Pair<>(CONTROL_AWB_MODE_INCANDESCENT, "AWB Disabled (Incandescent light: CIE A)"),
        new Pair<>(CONTROL_AWB_MODE_SHADE, "AWB Disabled (Shade light)"),
        new Pair<>(CONTROL_AWB_MODE_FLUORESCENT, "AWB Disabled (Fluorescent light: CIE F2)"),
        new Pair<>(CONTROL_AWB_MODE_WARM_FLUORESCENT, "AWB Disabled (Warm fluorescent light: CIE F4)")
    );

    private static final List<Pair<Integer, String>> ControlAfMode = Arrays.asList(
        new Pair<>(CONTROL_AF_MODE_OFF, "AF Disabled"),
        new Pair<>(CONTROL_AF_MODE_AUTO, "Basic automatic focus mode"),
        new Pair<>(CONTROL_AF_MODE_MACRO, "Close-up focusing mode"),
        new Pair<>(CONTROL_AF_MODE_CONTINUOUS_VIDEO, "Constantly-in-focus VIDEO stream"),
        new Pair<>(CONTROL_AF_MODE_CONTINUOUS_PICTURE, "Constantly-in-focus PICTURE stream"),
        new Pair<>(CONTROL_AF_MODE_EDOF, "Extended depth of field mode")
    );

    private static final List<Pair<Integer, String>> ControlAeMode = Arrays.asList(
        new Pair<>(CONTROL_AE_MODE_OFF, "Auto Exposure disabled"),
        new Pair<>(CONTROL_AE_MODE_ON, "Auto Exposure active"),
        new Pair<>(CONTROL_AE_MODE_ON_AUTO_FLASH, "Flash unit, firing it in low-light conditions"),
        new Pair<>(CONTROL_AE_MODE_ON_ALWAYS_FLASH, "Flash unit, always firing"),
        new Pair<>(CONTROL_AE_MODE_ON_AUTO_FLASH_REDEYE, "Automatic red eye reduction"),
        new Pair<>(CONTROL_AE_MODE_ON_EXTERNAL_FLASH, "External flash has been turned on")
    );

    public static String getInfoSupportedHardwareLevelComment(int key) {
        Optional<Pair<Integer, String>> matchLevel = InfoSupportedHardwareLevel.stream().filter(p -> p.first.equals(key)).findFirst();
        String result = "UNKNOWN";
        if (matchLevel.isPresent()) result = matchLevel.get().second;
        return result;
    }

    public static List<Pair<Integer, String>> getInfoSupportedHardwareLevelComment () {
        // return InfoSupportedHardwareLevel.stream().sorted().collect(Collectors.toList());
        // androidx.core.util.Pair cannot be cast to java.lang.Comparable
        return InfoSupportedHardwareLevel;
    }

    public static String getRequestAvailableCapabilitiesComment (int key) {
        Optional<Pair<Integer, String>> matchLevel = RequestAvailableCapabilities.stream().filter(p -> p.first.equals(key)).findFirst();
        String result = "UNKNOWN";
        if (matchLevel.isPresent()) result = matchLevel.get().second;
        return result;
    }

    public static List<Pair<Integer, String>> getRequestAvailableCapabilitiesComment () {
        return RequestAvailableCapabilities;
    }

    public static String getControlAwbModeComment (int key) {
        Optional<Pair<Integer, String>> matchLevel = ControlAwbMode.stream().filter(p -> p.first.equals(key)).findFirst();
        String result = "UNKNOWN";
        if (matchLevel.isPresent()) result = matchLevel.get().second;
        return result;
    }

    public static List<Pair<Integer, String>> getControlAwbModeComment () {
        return ControlAwbMode;
    }

    public static String getControlAfModeComment (int key) {
        Optional<Pair<Integer, String>> matchLevel = ControlAfMode.stream().filter(p -> p.first.equals(key)).findFirst();
        String result = "UNKNOWN";
        if (matchLevel.isPresent()) result = matchLevel.get().second;
        return result;
    }

    public static List<Pair<Integer, String>> getControlAfModeComment () {
        return ControlAfMode;
    }

    public static String getControlAeModeComment (int key) {
        Optional<Pair<Integer, String>> matchLevel = ControlAeMode.stream().filter(p -> p.first.equals(key)).findFirst();
        String result = "UNKNOWN";
        if (matchLevel.isPresent()) result = matchLevel.get().second;
        return result;
    }

    public static List<Pair<Integer, String>> getControlAeModeComment () {
        return ControlAeMode;
    }
}
