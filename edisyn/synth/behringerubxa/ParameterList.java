package edisyn.synth.behringerubxa;

public class ParameterList {


    public static final String[] ctrlGroups = {"Control", "Performance", "Envelopes", "Oscillators",
            "Filter", "ModMatrix", "Amplifier", "Panning", "Atrophy", "Sequencer", "Midi", "Keyboard", "Pedal", "Sysex", "Voice", "Surface", "Program", "UI", "Background"};

    public static final int NUM_PARAMS_DIALS = 5;
    public static final Object[] dials = {
            "ControlPortamentoAmount", 0, 0, 16383, false,
            "ControlPortamentoBend", 1, 0, 16383, false,
            "ControlDetune", 2, 0, 16383, true,
            "ControlVoiceDetune", 3, 0, 16383, false,
            "ControlUnison", 4, 0, 1, false,
            "ControlPolyphonicVoiceCount", 5, 1, 8, false,
            "ControlUnisonVoiceCount", 6, 1, 16, false,
            "ControlAftertouchSmoother", 8, 0, 127, false,
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
            "OscillatorsPWMDepth2", 537, 0, 255, false,
            "ControlRFU", 538, 0, 127, false,
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
            "FilterEnvChaos", 652, 0, 31, false,
            "FilterLFORange", 653, 0, 127, false,
            "FilterChaos", 654, 0, 63, false,
            "FilterDriftSpeed", 655, 0, 63, false,
            "FilterTrackOffset", 656, 0, 63, false,
            "FilterPedalRange", 657, 0, 96, false,
            "FilterVPOError", 658, 0, 63, false,
            "FilterVPOErrorChaos", 659, 0, 63, false,
            "FilterInitFreq", 660, 0, 127, false,
            "FilterTrackAmount", 664, 0, 64, false,
            "FilterEnvAttackLinearity", 665, 0, 31, false,
            "FilterEnvDecayLinearity", 666, 0, 31, false,
            "ModMatrixBus1Amount", 768, 0, 16383, true,
            "ModMatrixBus2Amount", 769, 0, 16383, true,
            "ModMatrixBus3Amount", 770, 0, 16383, true,
            "ModMatrixBus4Amount", 771, 0, 16383, true,
            "ModMatrixBus5Amount", 772, 0, 16383, true,
            "ModMatrixBus6Amount", 773, 0, 16383, true,
            "ModMatrixBus7Amount", 774, 0, 16383, true,
            "ModMatrixBus8Amount", 775, 0, 16383, true,
            "UIPatchNameA", 896, 0, 16383, false,
            "UIPatchNameB", 897, 0, 16383, false,
            "UIPatchNameC", 898, 0, 16383, false,
            "UIPatchNameD", 899, 0, 16383, false,
            "UIPatchNameE", 900, 0, 16383, false,
            "UIPatchNameF", 901, 0, 16383, false,
            "UIPatchNameG", 902, 0, 16383, false,
            "UIPatchNameH", 903, 0, 16383, false,
            "UILastUpperPatchNumber", 905, 0, 127, false,
            "UILastUpperPatchBank", 906, 0, 3, false,
            "UILastSplitProgramNumber", 907, 0, 35, false,
            "UILastDoubleProgramNumber", 908, 0, 35, false,
            "UIAtrophyProfileNumber", 909, 0, 7, false,
            "ArpeggiatorEnabled", 1024, 0, 1, false,
            "ArpeggiatorHold", 1026, 0, 1, false,
            "ArpeggiatorGatetime", 1028, 0, 99, false,
            "ArpeggiatorOctave", 1030, 1, 6, false,
            "ArpeggiatorSwing", 1031, 0, 10, false,
            "ArpeggiatorRepeat", 1032, 0, 10, false,
            "MidiTranspose", 1152, 0, 127, false,
            "MidiPatchTempo", 1153, 40, 240, false,
            "MidiTempo", 1154, 40, 240, false,
            "MidiGlobalTranspose", 1166, 0, 127, false,
            "KeyboardSplitPoint", 1280, 0, 127, false,
            "KeyboardVelocityScaling", 1282, 0, 127, false,
            "PedalVibratoLower", 1419, 0, 255, false,
            "PedalVibratoUpper", 1420, 0, 255, false,
            "PedalFilterLower", 1423, 0, 255, false,
            "PedalFilterUpper", 1424, 0, 255, false,
            "SurfaceBrightness", 1792, 0, 32, false,
            "SurfaceContrast", 1793, 0, 32, false,
            "AmplifierOffset2P", 2049, 0, 255, false,
            "AmplifierOffsetUni", 2050, 0, 255, false,
            "AmplifierOffsetMaster", 2051, 0, 255, false,
            "AmplifierEnvChaos", 2053, 0, 31, false,
            "AmplifierEnvAttackLinearity", 2054, 0, 31, false,
            "AmplifierEnvDecayLinearity", 2055, 0, 31, false,
            "AmplifierLFORange", 2056, 0, 127, false,
            "PanningVoice1", 2176, 0, 255, false,
            "PanningVoice2", 2177, 0, 255, false,
            "PanningVoice3", 2178, 0, 255, false,
            "PanningVoice4", 2179, 0, 255, false,
            "PanningVoice5", 2180, 0, 255, false,
            "PanningVoice6", 2181, 0, 255, false,
            "PanningVoice7", 2182, 0, 255, false,
            "PanningVoice8", 2183, 0, 255, false,
            "PanningVoice9", 2184, 0, 255, false,
            "PanningVoice10", 2185, 0, 255, false,
            "PanningVoice11", 2186, 0, 255, false,
            "PanningVoice12", 2187, 0, 255, false,
            "PanningVoice13", 2188, 0, 255, false,
            "PanningVoice14", 2189, 0, 255, false,
            "PanningVoice15", 2190, 0, 255, false,
            "PanningVoice16", 2191, 0, 255, false,
            "AtrophyProfileNameA", 2304, 32, 255, false,
            "AtrophyProfileNameB", 2305, 32, 255, false,
            "AtrophyProfileNameC", 2306, 32, 255, false,
            "AtrophyProfileNameD", 2307, 32, 255, false,
            "AtrophyProfileNameE", 2308, 32, 255, false,
            "AtrophyProfileNameF", 2309, 32, 255, false,
            "SequencerStepCount", 2432, 0, 128, false,
            "SequencerGatetime", 2434, 0, 99, false
    };

    public static final int NUM_PARAMS_CHECKBOXES = 4;
    public static final Object[] checkboxGroups = {
            "ControlPortamentoSettings", 7, 4, new String[]{"Match", "Quantize", "Bend", "Exponential"},
            "PerformanceSettings", 129, 6, new String[]{"Bend Osc2 only~Bend Osc1 & Osc2", "Custom bend~Bend +/- 2", "OSC1", "OSC2", "Upper", "Lower"},
            "PerformanceLFOMods", 135, 4, new String[]{"Track", "Envelope", "TempoLock", "LFOTrig"},
            "ModulationLFOMods", 261, 4, new String[]{"LFO Track on~LFO Track off", "LFOEnv2 Rate on~LFOEnv2 Rate off", "TempoLock", "LFOTrig"},
            "ModulationChannel1Sends", 263, 3, new String[]{"C1 OSC1 LFO on~C1 OSC1 LFO off", "C1 OSC2 LFO on~C1 OSC2 LFO off", "C1 Flt LFO on~C1 Flt LFO off"},
            "ModulationChannel1Mods", 264, 2, new String[]{"C1 Quant on~C1 Quant off", "LFOEnv1 Inv on~LFOEnv1 Inv off"},
            "ModulationChannel2Sends", 265, 3, new String[]{"C2 PWM1 LFO on~C2 PWM1 LFO off", "C2 PWM2 LFO on~C2 PWM2 LFO off", "C2 Vol LFO on~C2 Vol LFO off"},
            "ModulationChannel2Mods", 266, 2, new String[]{"C2 Quant on~C2 Quant off", "LFOEnv2 Inv on~LFOEnv2 Inv off"},
            "ModulationQuirks", 268, 2, new String[]{"FlipSquare", "FlipVCFMod"},
            "EnvelopesFilterMods", 397, 5, new String[]{"Flip", "Repeat", "Retrig", "Loop", "Legato"},
            "EnvelopesLoudnessMods", 398, 5, new String[]{"Flip", "Repeat", "Retrig", "Loop", "Legato"},
            "OscillatorsMode", 516, 2, new String[]{"OSC2 sync on~OSC2 sync off", "OSC2 f-env on~OSC2 f-env off"},
            "OscillatorsOSC1State", 517, 3, new String[]{"OSC1 full~OSC1 off", "OSC1 VCO LFO 180~OSC1 VCO LFO 0", "OSC1 PWM LFO 180~OSC1 PWM LFO 0"},
            "FilterModes", 644, 2, new String[]{"Filter track on~Filter track off", "4 pole filter~2 pole filter"},
            "UILastPatch", 910, 4, new String[]{"Upper", "Lower", "Combo", "Split"},
            "UIDisable", 911, 3, new String[]{"Assign Prset", "LED Segues", "Pulse revert"},
            "MidiSong", 1156, 5, new String[]{"Position In", "Stop on Seek", "Stop all off", "Start En Seq", "Start En Arp"},
            "MidiLocalControl", 1161, 8, new String[]{"Local Ctrl", "Din Rt", "Din Clock", "USB Rt", "USB Clock", "ProgChngeRx", "ProgChngeTx", "NRPNTx"},
            "MidiForwarding", 1162, 8, new String[]{"Din to Usb", "Usb to Din", "Din to Din", "Rt to Usb", "Rt to Din", "Clk to Din", "Clk to Usb", "ArpSeq Sel"},
            "MidiUSBControl", 1163, 4, new String[]{"USB NRPN TX", "USB NRPN RX", "USB CC TX", "USB CC RX"},
            "MidiDinControl", 1164, 4, new String[]{"DIN NRPN TX", "DIN NRPN RX", "DIN CC TX", "DIN CC RX"},
            "VoiceToggle1to8", 1664, 8, new String[]{"Voice 1", "Voice 2", "Voice 3", "Voice 4", "Voice 5", "Voice 6", "Voice 7", "Voice 8"},
            "VoiceToggle9to16", 1665, 8, new String[]{"Voice 9", "Voice 10", "Voice 11", "Voice 12", "Voice 13", "Voice 14", "Voice 15", "Voice 16"},
            "VoiceIgnorePWMCalibration1to8", 1666, 8, new String[]{"Voice 1", "Voice 2", "Voice 3", "Voice 4", "Voice 5", "Voice 6", "Voice 7", "Voice 8"},
            "VoiceIgnorePWMCalibration9to16", 1667, 8, new String[]{"Voice 9", "Voice 10", "Voice 11", "Voice 12", "Voice 13", "Voice 14", "Voice 15", "Voice 16"},
            "VoiceIgnoreVCOCalibration1to8", 1668, 8, new String[]{"Voice 1", "Voice 2", "Voice 3", "Voice 4", "Voice 5", "Voice 6", "Voice 7", "Voice 8"},
            "VoiceIgnoreVCOCalibration9to16", 1669, 8, new String[]{"Voice 9", "Voice 10", "Voice 11", "Voice 12", "Voice 13", "Voice 14", "Voice 15", "Voice 16"},
            "VoiceIgnoreVCFCalibration1to8", 1670, 8, new String[]{"Voice 1", "Voice 2", "Voice 3", "Voice 4", "Voice 5", "Voice 6", "Voice 7", "Voice 8"},
            "VoiceIgnoreVCFCalibration9to16", 1671, 8, new String[]{"Voice 9", "Voice 10", "Voice 11", "Voice 12", "Voice 13", "Voice 14", "Voice 15", "Voice 16"},
            "SurfaceLeverSettings", 1794, 3, new String[]{"Invert Left", "Invert Right", "Swap Levers"},
            "ProgramPerformancePanelOpts", 2560, 3, new String[]{"Save Patch", "Load Transp", "Transp MIDI"}
    };


    public static final int NUM_PARAMS_SELECTORS = 3;
    public static final Object[] selectors = {
            "ControlUnisonNotePriority", 9, new String[]{"Below", "Above", "Last"},
            "ControlChordModeNotePriority", 10, new String[]{"Below", "Above", "Last"},
            "PerformanceLFOShapes", 133, new String[]{"Sine", "Saw", "Square", "InverseSaw", "SH", "Triangle", "Sample Hold", "Noise"},
            "ModulationLFOShapes", 262, new String[]{"Sine", "Saw", "Square", "InverseSaw", "SH", "Triangle", "Sample Hold", "Noise"},
            "OscillatorsOSC1Shapes", 518, new String[]{"Osc1 pulse", "Osc1 saw", "Osc1 tri"},
            "OscillatorsOSC2State", 519, new String[]{"Osc2 half", "Osc2 full", "Osc2 off"},
            "OscillatorsOSC2Shapes", 520, new String[]{"Osc2 pulse", "Osc2 saw", "Osc2 tri"},
            "OscillatorsOSC1Quantization", 535, new String[]{"Octaves", "Semitones", "Free", "+/- Octaves", "+/- Semitones", "+/- Free"},
            "OscillatorsOSC2Quantization", 536, new String[]{"Octaves", "Semitones", "Free", "+/- Octaves", "+/- Semitones", "+/- Free"},
            "ModMatrixBus1Source", 776, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cuttoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus1Destination", 777, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv", "PulseWidth1Modulation", "PulseWidth2Modulation", "PanningLeftModulation", "PanningRightModulation", "OscillatorsDriftAmount", "OscillatorsDriftSpeed", "FilterDriftAmount", "FilterDriftSpeed"},
            "ModMatrixBus2Source", 778, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cuttoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus2Destination", 779, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv", "PulseWidth1Modulation", "PulseWidth2Modulation", "PanningLeftModulation", "PanningRightModulation", "OscillatorsDriftAmount", "OscillatorsDriftSpeed", "FilterDriftAmount", "FilterDriftSpeed"},
            "ModMatrixBus3Source", 780, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cuttoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus3Destination", 781, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv", "PulseWidth1Modulation", "PulseWidth2Modulation", "PanningLeftModulation", "PanningRightModulation", "OscillatorsDriftAmount", "OscillatorsDriftSpeed", "FilterDriftAmount", "FilterDriftSpeed"},
            "ModMatrixBus4Source", 782, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cuttoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus4Destination", 783, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv", "PulseWidth1Modulation", "PulseWidth2Modulation", "PanningLeftModulation", "PanningRightModulation", "OscillatorsDriftAmount", "OscillatorsDriftSpeed", "FilterDriftAmount", "FilterDriftSpeed"},
            "ModMatrixBus5Source", 784, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cuttoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus5Destination", 785, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv", "PulseWidth1Modulation", "PulseWidth2Modulation", "PanningLeftModulation", "PanningRightModulation", "OscillatorsDriftAmount", "OscillatorsDriftSpeed", "FilterDriftAmount", "FilterDriftSpeed"},
            "ModMatrixBus6Source", 786, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cuttoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus6Destination", 787, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv", "PulseWidth1Modulation", "PulseWidth2Modulation", "PanningLeftModulation", "PanningRightModulation", "OscillatorsDriftAmount", "OscillatorsDriftSpeed", "FilterDriftAmount", "FilterDriftSpeed"},
            "ModMatrixBus7Source", 788, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cuttoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus7Destination", 789, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv", "PulseWidth1Modulation", "PulseWidth2Modulation", "PanningLeftModulation", "PanningRightModulation", "OscillatorsDriftAmount", "OscillatorsDriftSpeed", "FilterDriftAmount", "FilterDriftSpeed"},
            "ModMatrixBus8Source", 790, new String[]{"Empty", "ChannelPressure", "PolyAftertouch", "BreathControl", "ModChannel1Envelope", "ModChannel2Envelope", "ModChannel1", "ModChannel2", "Expression", "Cuttoff", "FilterEnvelope", "KeyTrack", "KeyTrackPoly", "LoudnessEnvelope", "MainLFO", "SecondaryLFO", "Noise", "ModWheel", "Oscillator1", "Oscillator2", "PulseWidth1", "PulseWidth2", "PitchBendAmount", "Resonance", "VoiceSlewer", "PerformanceChannel", "PanningLeft", "PanningRight", "KeyVelocity", "SpreadFtn", "GeneralPurpose1", "GeneralPurpose2", "GeneralPurpose3", "GeneralPurpose4", "ModWheelUp", "ModWheelDown", "MPETimbre", "MPEPitch", "MPEPressure", "EightLEightR", "FlipFlop", "Spiral", "Osc1Drift", "Osc2Drift", "FilterDrift"},
            "ModMatrixBus8Destination", 791, new String[]{"Empty", "ButterInput", "EnvelopesFilterA", "EnvelopesFilterD", "FilterFrequency", "FilterModulation", "FilterNoise", "EnvelopesFilterR", "FilterResonance", "EnvelopesFilterS", "EnvelopesLoudnessA", "EnvelopesLoudnessD", "EnvelopesLoudnessR", "EnvelopesLoudnessS", "ModulationChannel1Amount", "ModulationChannel2Amount", "ModulationLFOPhase", "ModulationLFORate", "OscillatorsOSC1PWAmount", "OscillatorsOSC1Transpose", "ControlDetune", "OscillatorsOSC2PWAmount", "OscillatorsOSC2Transpose", "ControlPanning", "ControlPortamentoAmount", "PerformanceLFOChannelAmount", "PerformanceLFORate", "ManualMastertune", "ControlVoiceDetune", "PerformanceVolume", "CuttoffLfo", "Oscillator1Modulation", "Oscillator1Fenv", "Oscillator2Modulation", "Oscillator2Fenv", "PulseWidth1Modulation", "PulseWidth2Modulation", "PanningLeftModulation", "PanningRightModulation", "OscillatorsDriftAmount", "OscillatorsDriftSpeed", "FilterDriftAmount", "FilterDriftSpeed"},
            "UIVintageKnobs", 904, new String[]{"Extended", "Purism"},
            "UIAtrophyMode", 912, new String[]{"Read Only", "Edit Advanced"},
            "AtrophyProfile", 913, new String[]{"Atrophy Profile", "Current Patch"},
            "ArpeggiatorMode", 1025, new String[]{"Up", "Down", "Inclusive", "Exclusive", "Random", "Order", "Up x2", "Down x2", "Up x3", "Down x3", "Up x2/x3", "Down x2/ x3"},
            "ArpeggiatorTime", 1027, new String[]{"1/4 notes", "1/8 notes", "1/16 notes", "1/32 notes", "1/4 triplets", "1/8 triplets", "1/16 triplets"},
            "ArpeggiatorSync", 1029, new String[]{"Global", "Retrigger"},
            "MidiSync", 1155, new String[]{"Internal", "USB", "DIN"},
            "MidiChannelUpperRx", 1157, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16", "All", "None"},
            "MidiChannelLowerRx", 1158, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16", "All", "None"},
            "MidiChannelUpperTX", 1159, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16", "RxChannel"},
            "MidiChannelLowerTX", 1160, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16", "RxChannel"},
            "MidiMpeProfile", 1165, new String[]{"Disabled", "Single", "Zones"},
            "KeyboardVelocityCurviness", 1281, new String[]{"Soft", "Medium", "Hard"},
            "KeyboardAftertouchAssignment", 1283, new String[]{"ChannelPressure", "PolyAftertouch"},
            "KeyboardAftertouchCurviness", 1284, new String[]{"Soft", "Medium", "Hard"},
            "PedalSustainAssignment", 1408, new String[]{"Patch Up", "Patch Down", "Program Up", "Program Down", "Sustain", "Hold", "Sostenuto"},
            "PedalSustainPolarity", 1409, new String[]{"Negative", "Positive", "Disabled"},
            "PedalSustainInternalLatch", 1410, new String[]{"Unlatched", "Latched"},
            "PedalProgramAssignment", 1411, new String[]{"Patch Up", "Patch Down", "Program Up", "Program Down", "Sustain", "Hold", "Sostenuto"},
            "PedalProgramPolarity", 1412, new String[]{"Negative", "Positive", "Disabled"},
            "PedalProgramInternalLatch", 1413, new String[]{"Unlatched", "Latched"},
            "PedalHoldAssignment", 1414, new String[]{"Patch Up", "Patch Down", "Program Up", "Program Down", "Sustain", "Hold", "Sostenuto"},
            "PedalHoldPolarity", 1415, new String[]{"Negative", "Positive", "Disabled"},
            "PedalHoldInternalLatch", 1416, new String[]{"Unlatched", "Latched"},
            "PedalVibratoAssignment", 1417, new String[]{"Filter", "Vibrato", "Attack", "Release", "Decay", "General1 cc16", "General2 cc17", "General3 cc18", "General4 cc19"},
            "PedalVibratoPolarity", 1418, new String[]{"Negative", "Positive", "Disabled"},
            "PedalFilterAssignment", 1421, new String[]{"Filter", "Vibrato", "Attack", "Release", "Decay", "General1 cc16", "General2 cc17", "General3 cc18", "General4 cc19"},
            "PedalFilterPolarity", 1422, new String[]{"Negative", "Positive", "Disabled"},
            "SysexDeviceId", 1536, new String[]{"Channel 1", "Channel 2", "Channel 3", "Channel 4", "Channel 5", "Channel 6", "Channel 7", "Channel 8", "Channel 9", "Channel 10", "Channel 11", "Channel 12", "Channel 13", "Channel 14", "Channel 15", "Channel 16"},
            "SurfaceFan", 1795, new String[]{"Enabled", "TempCtrl", "Disabled"},
            "BackgroundCalibrationSettings", 1920, new String[]{"Enabled", "Only Auto", "Disabled"},
            "SequencerTime", 2433, new String[]{"1/4 notes", "1/8 notes", "1/16 notes", "1/32 notes", "1/4 triplets", "1/8 triplets", "1/16 triplets"},
            "SequencerSync", 2435, new String[]{"Global", "Retrigger"}
    };

    // Manual mapping for now - might be able to generate
    public static final Object[] SysExPosToKeyAndSize = {
            20, "EnvelopesFilterA",2,
            22, "EnvelopesFilterD",2,
            24, "EnvelopesFilterS",2,
            26, "EnvelopesFilterR",2,
            28, "EnvelopesLoudnessA",2,
            30, "EnvelopesLoudnessD",2,
            32, "EnvelopesLoudnessS",2,
            34, "EnvelopesLoudnessR",2,
    };

}
