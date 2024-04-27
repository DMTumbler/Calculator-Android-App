package com.example.mycalculatorjava;

import org.mariuszgromada.math.mxparser.*;

import android.os.Bundle;
import android.text.Editable;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private Editable text;
    private int cursorPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.textView).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String newString) {
        if (getString(R.string.textView).equals(display.getText().toString())) {
            display.setText("");
        }
        cursorPosition = display.getSelectionEnd();
        text = display.getText();
        text.insert(cursorPosition, newString);
    }

    public void zeroBTN(View view) {
        updateText(getString(R.string.zero));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void oneBTN(View view) {
        updateText(getString(R.string.one));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void twoBTN(View view) {
        updateText(getString(R.string.two));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void threeBTN(View view) {
        updateText(getString(R.string.three));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void fourBTN(View view) {
        updateText(getString(R.string.four));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void fiveBTN(View view) {
        updateText(getString(R.string.five));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void sixBTN(View view) {
        updateText(getString(R.string.six));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void sevenBTN(View view) {
        updateText(getString(R.string.seven));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void eightBTN(View view) {
        updateText(getString(R.string.eight));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void nineBTN(View view) {
        updateText(getString(R.string.nine));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void multiplyBTN(View view) {
        updateText(getString(R.string.multiply));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void divideBTN(View view) {
        updateText(getString(R.string.division));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void subtractBTN(View view) {
        updateText(getString(R.string.subtract));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void addBTN(View view) {
        updateText(getString(R.string.add));
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void dotBTN(View view) {
        if (!display.getText().toString().contains(".")) {
            updateText(getString(R.string.dot));
        }
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    public void equalBTN(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll(getString(R.string.division), "/");
        userExp = userExp.replaceAll(getString(R.string.multiply), "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void percentBTN(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        updateText(getString(R.string.percent));
    }

    public void deleteBTN(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        cursorPosition = display.getSelectionEnd();
        text = display.getText();
        if (display.getSelectionStart() != display.getSelectionEnd()) {
            text.delete(display.getSelectionStart(), display.getSelectionEnd());
        } else if (cursorPosition > 0) {
            text.delete(cursorPosition - 1, cursorPosition);
        }
    }

    public void parenthesesBTN(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        String oldString = display.getText().toString();
        String leftString = oldString.substring(0, cursorPosition);
        long countOpen = leftString.chars().filter(ch -> ch == '(').count();
        long countClose = leftString.chars().filter(ch -> ch == ')').count();

        if (countOpen > countClose) {
            updateText(getString(R.string.closeParen));
        } else {
            updateText(getString(R.string.openParen));
        }
    }


    public void acBTN(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        display.setText("");
    }
}
