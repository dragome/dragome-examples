package com.dragome.examples;

enum Operation
{
    add
    {
        public float apply(float result, int value)
        {
    	return result + value;
        }
    },
    sub
    {
        public float apply(float result, int value)
        {
    	return result - value;
        }
    },
    mul
    {
        public float apply(float result, int value)
        {
    	return result * value;
        }
    },
    div
    {
        public float apply(float result, int value)
        {
    	return result / value;
        }
    };

    public float apply(float result, int value)
    {
        return 0;
    }
}