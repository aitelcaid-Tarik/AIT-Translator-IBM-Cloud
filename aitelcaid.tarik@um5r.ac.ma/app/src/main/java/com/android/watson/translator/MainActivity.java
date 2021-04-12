package com.android.watson.translator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;


public class MainActivity extends AppCompatActivity {

    TextInputEditText srcTextView;
    Button translateButton;
    TextView targetTextView;
    Spinner sourceLangSelector, targetLangSelector;
    Button buttonSwitchLang;

    final String languageOptions[] = {
            "Arabic",
            "Chinese",
            "English",
            "French",
            "Portuguese",
            "German",
            "Spanish"
    };

    final String code[] = {
            Language.ARABIC,
            Language.CHINESE,
            Language.ENGLISH,
            Language.FRENCH,
            Language.PORTUGUESE,
            Language.GERMAN,
            Language.SPANISH
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srcTextView = findViewById(R.id.sourceText);
        translateButton = findViewById(R.id.translateButton);
        targetTextView = findViewById(R.id.targetText);
        sourceLangSelector = findViewById(R.id.sourceLangSelector);
        targetLangSelector = findViewById(R.id.targetLangSelector);
        buttonSwitchLang =  findViewById(R.id.buttonSwitchLang);

        sourceLangSelector.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, languageOptions));
        targetLangSelector.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, languageOptions));

        sourceLangSelector.setSelection(2);
        targetLangSelector.setSelection(0);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String srctext = srcTextView.getText().toString();

                if (sourceLangSelector.getSelectedItemPosition() == targetLangSelector.getSelectedItemPosition()){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Translation Error")
                            .setMessage("Source and target languages cannot be the same!")
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                    return;
                }
                if (!srctext.trim().equals(""))
                    new WatsonTranslatorTask().execute(srctext, code[sourceLangSelector.getSelectedItemPosition()], code[targetLangSelector.getSelectedItemPosition()]);
                else
                    new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Translation Error")
                        .setMessage("Source text can not be empty!")
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
            }
        });

        buttonSwitchLang.setOnClickListener(view ->{
            final int source = sourceLangSelector.getSelectedItemPosition();
            final int target = targetLangSelector.getSelectedItemPosition();
            sourceLangSelector.setSelection(target);
            targetLangSelector.setSelection(source);
        });
    }

    private class WatsonTranslatorTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            return translate(MainActivity.this, strings);

        }

        @Override
        protected void onPostExecute(String result) {
            targetTextView.setText(result);
        }
    }


    public String translate(Context context, String... strings) {

        IamAuthenticator iamAuthenticator = new IamAuthenticator(context.getString(R.string.api_key));
        LanguageTranslator languageTranslator = new LanguageTranslator("2021-04-06", iamAuthenticator);
        languageTranslator.setServiceUrl(context.getString(R.string.url));

        TranslateOptions translateOptions = new TranslateOptions.Builder().addText(strings[0])
                .source(strings[1])
                .target(strings[2])
                .build();

        TranslationResult translationResult = languageTranslator.translate(translateOptions)
                .execute()
                .getResult();

        return translationResult.getTranslations().get(0).getTranslation();
    }
}
