package edisyn.synth.behringerubxa;

import edisyn.Midi;
import edisyn.Synth;
import edisyn.gui.*;

import javax.swing.*;
import java.awt.*;

public class BehringerUBXa extends Synth {

    private static final int NUM_PARAMS = 5;

    private final Object[] dials = {
            "ControlPortamentoAmount", 0, 0, 16383, false,
            "ControlPortamentoBend", 1, 0, 16383, false,
            "ControlDetune", 2, 0, 16383, true,
            "ControlVoiceDetune", 3, 0, 16383, false,
            "ControlUnison", 4, 0, 1, false,
            "ControlPolyphonicVoiceCount", 5, 1, 8, false,
            "ControlUnisonVoiceCount", 6, 1, 16, false,
            "ControlPortamentoSpread", 11, 0, 255, false,
            "ControlPortamentoRange", 12, 0, 255, false,
            "PerformanceVolume", 128, 0, 16383, false,
            "PerformancePitchBendSensitivity", 130, 0, 16383, false,
            "PerformanceTranspose", 131, 0, 16383, true,
            "PerformanceLFORate", 132, 0, 16383, false,
            "PerformanceLFOChannelAmount", 134, 0, 16383, false,
            "PerformanceMpePitchLowerSensitivity", 136, 0, 96, false,
            "PerformanceMpePitchUpperSensitivity", 137, 0, 96, false,
            "PerformanceLFOTrim", 138, 0, 255, false,
            "ModulationLFOTrigPoint", 256, 0, 16383, false,
            "ModulationLFORate", 257, 0, 16383, false,
            "ModulationLFOPhase", 258, 0, 16383, false,
            "ModulationChannel1Amount", 259, 0, 16383, false,
            "ModulationChannel2Amount", 260, 0, 16383, false,
            "ModulationLFOTrim", 267, 0, 255, false,
            "EnvelopesFilterA", 384, 0, 16383, false,
            "EnvelopesFilterD", 385, 0, 16383, false,
            "EnvelopesFilterS", 386, 0, 16383, false,
            "EnvelopesFilterR", 387, 0, 16383, false,
            "EnvelopesLoudnessA", 388, 0, 16383, false,
            "EnvelopesLoudnessD", 389, 0, 16383, false,
            "EnvelopesLoudnessS", 390, 0, 16383, false,
            "EnvelopesLoudnessR", 391, 0, 16383, false,
            "EnvelopesModChannel1A", 392, 0, 16383, false,
            "EnvelopesModChannel1Delay", 393, 0, 16383, false,
            "EnvelopesModChannel2A", 394, 0, 16383, false,
            "EnvelopesModChannel2Delay", 395, 0, 16383, false,
            "EnvelopesPedalR", 396, 0, 16383, false,
            "OscillatorsOSC1PWAmount", 512, 0, 16383, false,
            "OscillatorsOSC1Transpose", 513, 0, 16383, true,
            "OscillatorsOSC2PWAmount", 514, 0, 16383, false,
            "OscillatorsOSC2Transpose", 515, 0, 16383, true,
            "OscillatorsOSC1PWTrimL", 521, 0, 255, false,
            "OscillatorsOSC1PWTrimR", 522, 127, 255, false,
            "OscillatorsOSC2PWTrimL", 523, 0, 255, false,
            "OscillatorsOSC2PWTrimR", 524, 127, 255, false,
            "OscillatorsModDepth", 525, 6, 63, false,
            "OscillatorsPWMDepth", 526, 0, 255, false,
            "OscillatorsPWMOffsetShift", 527, 0, 255, false,
            "OscillatorsChaos", 528, 0, 63, false,
            "OscillatorsVPOError", 529, 0, 31, false,
            "OscillatorsDriftSpeed", 530, 0, 63, false,
            "OscillatorsVPOErrorChaos", 531, 0, 63, false,
            "OscillatorsOscAutoInitF", 532, 0, 127, false,
            "OscillatorsFEnvRange", 533, 1, 63, false,
            "OscillatorsModDepth2", 534, 6, 63, false,
            "FilterFrequency", 640, 0, 16383, false,
            "FilterResonance", 641, 0, 16383, false,
            "FilterModulation", 642, 0, 16383, false,
            "FilterNoise", 643, 0, 16383, false,
            "FilterResonanceTrim", 645, 0, 255, false,
            "FilterResonanceTrim4Pole", 646, 0, 255, false,
            "FilterFrequencyRange", 647, 0, 127, false,
            "FilterZero2pOffset", 648, 0, 48, false,
            "FilterZero4pOffset", 649, 0, 48, false,
            "FilterModulationRange", 650, 0, 127, false,

    };

    public static String getSynthName() {
        return "Behringer UB-Xa";
    }

    public static String[] splitAtCapitalLetter(String input, int k) {
        // Check if the input length is greater than k
        if (input.length() <= k) {
            // If not, return the input as a single string in an array
            return new String[]{input};
        }

        int midpoint = input.length() / 2;

        // Search backward from the midpoint for a capital letter
        for (int i = midpoint; i > 0; i--) {
            if (Character.isUpperCase(input.charAt(i))) {
                return new String[]{
                        input.substring(0, i),
                        input.substring(i)
                };
            }
        }

        // If no capital letter is found before midpoint, search forward
        for (int i = midpoint; i < input.length(); i++) {
            if (Character.isUpperCase(input.charAt(i))) {
                return new String[]{
                        input.substring(0, i),
                        input.substring(i)
                };
            }
        }

        // If no capital letter is found, return the entire string as a single string in the array
        return new String[]{input};
    }

    public BehringerUBXa() {

        assert dials.length % NUM_PARAMS == 0;


        JComponent soundPanel = new SynthPanel(this);
        addTab("Controls", soundPanel);
        JComponent vb = new VBox();
        soundPanel.add(vb, BorderLayout.CENTER);

        JComponent container = null;

        for (int i = 0; i < dials.length; i += NUM_PARAMS) {
            if (i % (NUM_PARAMS * 10) == 0){
                container = new HBox();
                vb.add(container);
            }
            String label = (String) dials[i];
            String key = (String) dials[i];
            int minVal = (int) dials[i + 2];
            int maxVal = (int) dials[i + 3];
            boolean symmetric = (boolean) dials[i + 4];
            int sub = symmetric ? maxVal / 2 + 1 : 0;

            String[] lbls = splitAtCapitalLetter(label, 10);


            LabelledDial comp = new LabelledDial(lbls[0], this, key, Style.COLOR_A(), minVal, maxVal, sub) {
                public boolean isSymmetric() {
                    return symmetric;
                }
            };
            if (lbls.length > 1) {
                comp.addAdditionalLabel(lbls[1]);
            }
            container.add(comp);

        }
    }

    @Override
    public Object[] emitAll(String key) {
        int param = 0;
        for (int i = 0; i < dials.length; i += NUM_PARAMS) {
            String label = (String) dials[i];
            if (label.equals(key)) {
                param = (int) dials[i + 1];
                int val = getModel().get(key);
                return buildNRPN(getChannelOut(),
                        param, val);
            }
        }

        assert false;
        return null;
    }

    @Override
    public void handleSynthCCOrNRPN(Midi.CCData data) {
        for (int i = 0; i < dials.length; i += NUM_PARAMS) {
            if (dials[i+1].equals(data.number)) {
                String label = (String) dials[i];
                getModel().set(label, data.value);
                break;
            }
        }
    }
}