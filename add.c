#include<jni.h>
#include<stdio.h>
#include "testJni.h" 

JNIEXPORT jint JNICALL Java_testJni_add(JNIEnv *e, jobject obj, jint a, jint b)
{
	jint res;
	res = a + b;
	return res;
}

multiplication
#include<jni.h>
#include<stdio.h>
#include "testJni.h" 

JNIEXPORT jint JNICALL Java_testJni_mul(JNIEnv *e, jobject o, jint a,  jint b)
{
	jint res;
	res = a * b;
	return res;
}

square
#include<jni.h>
#include<stdio.h>
#include "testJni.h" 

JNIEXPORT jint JNICALL Java_testJni_sqr(JNIEnv *e, jobject o, jint a)
{
	jint res;
	res = a * a;
	return res;
}

SUB
#include<jni.h>
#include<stdio.h>
#include "testJni.h" 


JNIEXPORT jint JNICALL Java_testJni_sub(JNIEnv *e, jobject o, jint a, jint b)
{
	jint res;
	res = a - b;
	return res;
}