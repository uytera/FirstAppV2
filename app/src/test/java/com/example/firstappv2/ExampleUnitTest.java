//package com.example.firstappv2;
//
//
//import android.os.Build;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.fragment.app.ListFragment;
//
//import com.example.firstappv2.fragments.main.MainFragment;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//import org.robolectric.util.FragmentTestUtil;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertNotNull;
//
//
//@RunWith(RobolectricTestRunner.class)
//@Config(sdk = {Build.VERSION_CODES.O_MR1})
//public class ExampleUnitTest {
////    private NavMainActivity activity;
////
////    @Test
////    public void mainPage() {
////        NavMainActivity activity = Robolectric.setupActivity(NavMainActivity.class);
////
////        TextView results = activity.findViewById(R.id.topText);
////
////        assertThat(results.getText().toString()).isEqualTo("WeatherApp");
////    }
////
////    @Test
////    public void listPage() {
////        NavMainActivity activity = Robolectric.buildActivity(NavMainActivity.class).create().start().resume().get();
////
////        MainFragment mainFragment = new MainFragment();
////
////        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(mainFragment.getTag());
////        assertNotNull(fragment);
////        ListFragment loginFragment = new ListFragment();
////
////        FragmentManager fragmentManager = activity.getSupportFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.replace(R.id.nav_host_fragment, new ListFragment());
////        fragmentTransaction.commit();
////
////        TextView results = activity.findViewById(R.id.topText);
////
////        assertThat(results.getText().toString()).isEqualTo("WeatherApp");
//    }
//}
//
////        NavMainActivity activity = Robolectric.setupActivity(NavMainActivity.class);
////
////        com.google.android.material.textfield.TextInputLayout editTextMaterial = activity.findViewById(R.id.nomen);
////        EditText editText = editTextMaterial.getEditText();
////        Button deepButton = activity.findViewById(R.id.deepButton);
////
////        editText.setText("Париж");
////        deepButton.performClick();
////
////        AlertDialog dialog = activity.showDialog(1);
////
////        Button confirm = dialog.getButton(Dialog.BUTTON_POSITIVE);
////        confirm.performClick();
////
////        FragmentManager fragmentManager = activity.getSupportFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.add(new WeatherFragment(), null );
////        fragmentTransaction.commit();
////        View root = inflater.inflate(R.layout.tenebris_layout, container, false);
////
////        city = root.findViewById(R.id.city);
////        temp = root.findViewById(R.id.temp);
////        windSp = root.findViewById(R.id.windSp);
////        WeatherFragment fragment = new WeatherFragment();
////
////        TextView city = WeatherFragment. .findViewById(R.id.city);
////
////        assertThat(city.getText().toString()).isEqualTo("Париж");
////        TextView results = activity.findViewById(R.id.topText);
////
////        assertThat(results.getText().toString()).isEqualTo("WeatherApp");