package text.example.com.textinput;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import text.example.com.textinput.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.myLayout.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        binding.myLayout.setFocusableInTouchMode(true);
        binding.btnGreen.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                setInputTextLayoutColor(ContextCompat.getColor(activity, R.color.green), binding.textInputLayout2);
                ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.green));
                binding.etEditText.setSupportBackgroundTintList(colorStateList);
            }
        });

        binding.btnRed.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                setInputTextLayoutColor(ContextCompat.getColor(activity, R.color.red), binding.textInputLayout2);
                ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.red));
                binding.etEditText.setSupportBackgroundTintList(colorStateList);
            }
        });

    }

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
}
