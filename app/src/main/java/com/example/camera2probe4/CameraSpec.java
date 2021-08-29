// Original Code: TobiasWeis / android-camera2probe
// https://github.com/TobiasWeis/android-camera2probe/wiki
//
// Ported to Android Studio 4.2.1, API 29 and Java 8
// Toyoaki, OHGOCHI  https://twitter.com/Ohgochi/

package com.example.camera2probe4;

import static android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES;
import static android.hardware.camera2.CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES;
import static android.hardware.camera2.CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES;
import static android.hardware.camera2.CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES;

import android.content.Context;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;

import static com.example.camera2probe4.CameraSpecsComment.getInfoSupportedHardwareLevelComment;
import static com.example.camera2probe4.CameraSpecsComment.getRequestAvailableCapabilitiesComment;
import static com.example.camera2probe4.CameraSpecsComment.getControlAwbModeComment;
import static com.example.camera2probe4.CameraSpecsComment.getControlAfModeComment;
import static com.example.camera2probe4.CameraSpecsComment.getControlAeModeComment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CameraSpec {
    CameraCharacteristics characteristics = null;
    android.hardware.camera2.CameraManager manager;
    String[] cameraIds = null;

    List<CameraSpecResult> specs = new ArrayList<>();

    public static final int NONE = -1;
    public static final int CROSS = 0;
    public static final int CHECK = 1;

    public static final String KEY_TITLE ="TITLE";
    public static final String KEY_LOGICAL ="LOGICAL CAMERA";
    public static final String KEY_PHYSICAL ="PHYSICAL CAMERA";

    CameraSpec(Context context) {
        manager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraIds = manager.getCameraIdList();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    void setCharacteristics(String CameraId) {
        try {
            characteristics = manager.getCameraCharacteristics(CameraId);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public List<CameraSpecResult> getSpecs() {
        return specs;
    }

    public void getCameraSpec() {
        getModel();
        for (String id: cameraIds) {
            specs.add(new CameraSpecResult(KEY_LOGICAL, id, NONE));
            setCharacteristics(id);
            getHwLevel();
            getAvailableCapabilities();
            getAwbCapabilities();
            getAfCapabilities();
            getAeCapabilities();
        }
    }

    private void getModel() {
        specs.add(new CameraSpecResult(KEY_TITLE, "MODEL", NONE));
        specs.add(new CameraSpecResult("Model", Build.MODEL, NONE));
        specs.add(new CameraSpecResult("Manufacturer", Build.MANUFACTURER, NONE));
        specs.add(new CameraSpecResult("Build version", android.os.Build.VERSION.RELEASE, NONE));
        specs.add(new CameraSpecResult("SDK version", String.valueOf(android.os.Build.VERSION.SDK_INT), NONE));
        specs.add(new CameraSpecResult("Logical Cameras", String.valueOf(cameraIds.length), NONE));
        // specs.add(new Triple<>("Logical Cameras", String.valueOf(cameraIds.length), NONE));
    }

    private void getHwLevel() {
        int mylevel = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        specs.add(new CameraSpecResult(KEY_TITLE, "Hardware Level Support Category", NONE));
        specs.add(new CameraSpecResult("Category", getInfoSupportedHardwareLevelComment(mylevel), NONE));
     }

    private void getAvailableCapabilities() {
        int[] capabilities= characteristics.get(REQUEST_AVAILABLE_CAPABILITIES);
        List<Integer> capabilitiesList = (List<Integer>) Arrays.stream(capabilities).boxed().collect(Collectors.toList());
        specs.add(new CameraSpecResult(KEY_TITLE, "Request Available Capabilities", NONE));
        getRequestAvailableCapabilitiesComment().forEach( p -> {
            if (capabilitiesList.contains(p.first) )
                specs.add(new CameraSpecResult(p.second, "", CHECK));
            else
                specs.add(new CameraSpecResult(p.second, "", CROSS));
        });
    }

    private void getAwbCapabilities() {
        int[] capabilities= characteristics.get(CONTROL_AWB_AVAILABLE_MODES);
        List<Integer> capabilitiesList = (List<Integer>) Arrays.stream(capabilities).boxed().collect(Collectors.toList());
        specs.add(new CameraSpecResult(KEY_TITLE, "Auto White Balance Capabilities", NONE));
        getControlAwbModeComment().forEach( p -> {
            if (capabilitiesList.contains(p.first) )
                specs.add(new CameraSpecResult(p.second, "", CHECK));
            else
                specs.add(new CameraSpecResult(p.second, "", CROSS));
        });
    }

    private void getAfCapabilities() {
        int[] capabilities= characteristics.get(CONTROL_AF_AVAILABLE_MODES);
        List<Integer> capabilitiesList = (List<Integer>) Arrays.stream(capabilities).boxed().collect(Collectors.toList());
        specs.add(new CameraSpecResult(KEY_TITLE, "Auto Focus Capabilities", NONE));
        getControlAfModeComment().forEach( p -> {
            if (capabilitiesList.contains(p.first) )
                specs.add(new CameraSpecResult(p.second, "", CHECK));
            else
                specs.add(new CameraSpecResult(p.second, "", CROSS));
        });
    }

    private void getAeCapabilities() {
        int[] capabilities= characteristics.get(CONTROL_AE_AVAILABLE_MODES);
        List<Integer> capabilitiesList = (List<Integer>) Arrays.stream(capabilities).boxed().collect(Collectors.toList());
        specs.add(new CameraSpecResult(KEY_TITLE, "Auto Exposure Capabilities", NONE));
        getControlAeModeComment().forEach( p -> {
            if (capabilitiesList.contains(p.first) )
                specs.add(new CameraSpecResult(p.second, "", CHECK));
            else
                specs.add(new CameraSpecResult(p.second, "", CROSS));
        });
    }
}
