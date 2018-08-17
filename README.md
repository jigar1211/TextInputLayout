Change TextInput layout color dynamically

Add following code:

++++++++++++++++++++++++++++++++++++++++++
  
  setInputTextLayoutColor(ContextCompat.getColor(activity, R.color.green), binding.textInputLayout2);
                ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.green));
                binding.etEditText.setSupportBackgroundTintList(colorStateList);
                
++++++++++++++++++++++++++++++++++++++++++

   private void setInputTextLayoutColor(int color, TextInputLayout textInputLayout) {
        
        try {
        
            Field field = textInputLayout.getClass().getDeclaredField("mFocusedTextColor");
            field.setAccessible(true);
            int[][] states = new int[][]{
                    new int[]{}
            };
            int[] colors = new int[]{
                    color
            };
            ColorStateList myList = new ColorStateList(states, colors);
            field.set(textInputLayout, myList);

            Field fDefaultTextColor = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
            fDefaultTextColor.setAccessible(true);
            fDefaultTextColor.set(textInputLayout, myList);

            Method method = textInputLayout.getClass().getDeclaredMethod("updateLabelState", boolean.class);
            method.setAccessible(true);
            method.invoke(textInputLayout, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }             
