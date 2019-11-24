package me.programmeris.myresume.api.entity.content;

import com.google.common.collect.Lists;

import java.util.List;

public class ContentType {
    public static final String PROFILE = "PROFILE";
    public static final String EDUCATION = "EDUCATION";
    public static final String CERTIFICATION = "CERTIFICATION";
    public static final String INTEREST = "INTEREST";
    public static final String WORK_EXPERIENCE = "WORK_EXPERIENCE";
    public static final String PROJECT_EXPERIENCE = "PROJECT_EXPERIENCE";
    public static final String SKILL = "SKILL";
    public static final String SELF_INTRODUCTION = "SELF_INTRODUCTION";
    public static final String CONTACT = "CONTACT";

    private static final List<String> values = Lists.newArrayList(PROFILE,
                                                                  EDUCATION,
                                                                  CERTIFICATION,
                                                                  INTEREST,
                                                                  WORK_EXPERIENCE,
                                                                  PROJECT_EXPERIENCE,
                                                                  SKILL,
                                                                  SELF_INTRODUCTION,
                                                                  CONTACT);
}
