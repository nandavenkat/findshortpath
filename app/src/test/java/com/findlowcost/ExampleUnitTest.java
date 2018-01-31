package com.findlowcost;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21,manifest = "src/main/AndroidManifest.xml", packageName = "com.findlowcost")
public class ExampleUnitTest {

    FindLowCostActivity activity = Robolectric.setupActivity(FindLowCostActivity.class);


    @Test
    public void goButtonIsDisabledByDefault() {
        Button goButton = (Button) activity.findViewById (R.id.go_button);
        assertThat(goButton.isEnabled(), is(false));
    }

    @Test
    public void clickingGoWithLessThanFiveColumnsOfDataDisplaysErrorMessage() {
        EditText customGridContents = (EditText)  activity.findViewById(R.id.custom_grid_contents);

        customGridContents.setText("1 2 3 4");
        activity.findViewById(R.id.go_button).performClick();

        ShadowAlertDialog alertDialog = Shadows.shadowOf(ShadowAlertDialog.getLatestAlertDialog());
        assertThat(alertDialog.getTitle().toString(), equalTo("Invalid Grid"));
        assertThat(alertDialog.getMessage().toString(), equalTo(activity.getResources().getString(R.string.invalid_grid_message)));
    }

    @Test
    public void clickingGoWithMoreThanOneHundredColumnsOfDataDisplaysErrorMessage() {
        EditText customGridContents = (EditText) activity.findViewById(R.id.custom_grid_contents);
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 1; i <= 101; i++) {
            inputBuilder.append(i).append(" ");
        }

        customGridContents.setText(inputBuilder.toString());
        activity.findViewById(R.id.go_button).performClick();

        ShadowAlertDialog alertDialog = Shadows.shadowOf(ShadowAlertDialog.getLatestAlertDialog());
        assertThat(alertDialog.getTitle().toString(), equalTo("Invalid Grid"));
        assertThat(alertDialog.getMessage().toString(), equalTo(activity.getResources().getString(R.string.invalid_grid_message)));
    }
    @Test
    public void clickingGoWithValidDataDisplaysYesIfPathSuccessful() {
        EditText customGridContents = (EditText) activity.findViewById(R.id.custom_grid_contents);

        customGridContents.setText("3 4 1 2 8 6\n" +
                                   "6 1 8 2 7 4\n" +
                                   "5 9 3 9 9 5\n" +
                                   "8 4 1 3 2 6\n" +
                                   "3 7 2 8 6 4");
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_success);
        TextView totalCost = (TextView)  activity.findViewById(R.id.results_total_cost);
        TextView pathTaken = (TextView)  activity.findViewById(R.id.results_path_taken);
        assertThat(resultsView.getText().toString(), equalTo("Yes"));
        assertThat(totalCost.getText().toString(), equalTo("16"));
        assertThat(pathTaken.getText().toString().replace("\t", " "), equalTo("1 2 3 4 4 5"));

    }
    @Test
    public void clickingGoWithValidDataDisplaysYesIfPathSuccessfulsixfive() {
        EditText customGridContents = (EditText) activity.findViewById(R.id.custom_grid_contents);

        customGridContents.setText("3 4 1 2 8 6\n" +
                "6 1 8 2 7 4\n" +
                "5 9 3 9 9 5\n" +
                "8 4 1 3 2 6\n" +
                "3 7 2 1 2 3");
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_success);
        TextView totalCost = (TextView)  activity.findViewById(R.id.results_total_cost);
        TextView pathTaken = (TextView)  activity.findViewById(R.id.results_path_taken);
        assertThat(resultsView.getText().toString(), equalTo("Yes"));
        assertThat(totalCost.getText().toString(), equalTo("11"));
        assertThat(pathTaken.getText().toString().replace("\t", " "), equalTo("1 2 1 5 4 5"));

    }
    @Test
    public void clickingGoWithValidDataDisplaysNoPathSuccessfullessfifty() {
        EditText customGridContents = (EditText) activity.findViewById(R.id.custom_grid_contents);

        customGridContents.setText("19 10 19 10 19\n" +
                "21 23 20 19 12\n" +
                "20 12 20 11 10\n"
               );
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_success);
        TextView totalCost = (TextView)  activity.findViewById(R.id.results_total_cost);
        TextView pathTaken = (TextView)  activity.findViewById(R.id.results_path_taken);
        assertThat(resultsView.getText().toString(), equalTo("No"));
        assertThat(totalCost.getText().toString(), equalTo("48"));
        assertThat(pathTaken.getText().toString().replace("\t", " "), equalTo("1 1 1"));

    }
    @Test
    public void clickingGoWithValidDataDisplaysYesIfPathSuccessfulonefive() {
        EditText customGridContents = (EditText) activity.findViewById(R.id.custom_grid_contents);

        customGridContents.setText("5 8 5 3 5");
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_success);
        TextView totalCost = (TextView)  activity.findViewById(R.id.results_total_cost);
        TextView pathTaken = (TextView)  activity.findViewById(R.id.results_path_taken);
        assertThat(resultsView.getText().toString(), equalTo("Yes"));
        assertThat(totalCost.getText().toString(), equalTo("26"));
        assertThat(pathTaken.getText().toString().replace("\t", " "), equalTo("1 1 1 1 1"));

    }

    @Test
    public void clickingGoWithValidDataDisplaysYesIfPathSuccessfulfiveoneTest() {
        EditText customGridContents = (EditText) activity.findViewById(R.id.custom_grid_contents);

        customGridContents.setText("5\n"
                                   +"8\n"
                                   +"5\n"
                                   +"3\n"
                                   +"5");
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_success);
        TextView totalCost = (TextView)  activity.findViewById(R.id.results_total_cost);
        TextView pathTaken = (TextView)  activity.findViewById(R.id.results_path_taken);
        assertThat(resultsView.getText().toString(), equalTo("Yes"));
        assertThat(totalCost.getText().toString(), equalTo("3"));
        assertThat(pathTaken.getText().toString().replace("\t", " "), equalTo("4"));

    }
    @Test
    public void clickingGoWithValidDataDisplaysYesIfPathSuccessfulmorefifty() {
        EditText customGridContents = (EditText) activity.findViewById(R.id.custom_grid_contents);

        customGridContents.setText("69 10 19 10 19\n" +
                "51 23 20 19 12\n" +
                "60 12 20 11 10\n"
        );
        activity.findViewById(R.id.go_button).performClick();

        TextView resultsView = (TextView) activity.findViewById(R.id.results_success);
        TextView totalCost = (TextView)  activity.findViewById(R.id.results_total_cost);
        TextView pathTaken = (TextView)  activity.findViewById(R.id.results_path_taken);
        assertThat(resultsView.getText().toString(), equalTo("No"));
        assertThat(totalCost.getText().toString(), equalTo("0"));
        assertThat(pathTaken.getText().toString().replace("\t", " "), equalTo(" "));

    }

}