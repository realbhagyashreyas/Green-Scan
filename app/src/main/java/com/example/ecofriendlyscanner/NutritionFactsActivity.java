package com.example.ecofriendlyscanner;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NutritionFactsActivity extends AppCompatActivity {

    private OpenFoodFactsApi openFoodFactsApi;
    private TextView energy;
    private TextView fat;
    private TextView saturatedFat;
    private TextView carbohydrates;
    private TextView sugars;
    private TextView fiber;
    private TextView proteins;
    private TextView salt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_facts);

        // Initialize views
        energy = findViewById(R.id.energy);
        fat = findViewById(R.id.fat);
        saturatedFat = findViewById(R.id.saturated_fat);
        carbohydrates = findViewById(R.id.carbohydrates);
        sugars = findViewById(R.id.sugars);
        fiber = findViewById(R.id.fiber);
        proteins = findViewById(R.id.proteins);
        salt = findViewById(R.id.salt);

        openFoodFactsApi = ApiClient.getClient().create(OpenFoodFactsApi.class);

        String barcode = getIntent().getStringExtra("barcode");
        if (barcode != null) {
            fetchNutritionFacts(barcode);
        } else {
            Toast.makeText(this, "No barcode found.", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchNutritionFacts(String barcode) {
        openFoodFactsApi.getProductInfo(barcode).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductResponse productResponse = response.body();
                    if (productResponse.getStatus() == 1) {
                        ProductResponse.Product product = productResponse.getProduct();
                        displayNutritionFacts(product);
                    } else {
                        showProductNotFoundMessage();
                    }
                } else {
                    showProductNotFoundMessage();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(NutritionFactsActivity.this, "Error fetching nutrition data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayNutritionFacts(ProductResponse.Product product) {
        ProductResponse.Product.Nutriments nutriments = product.getNutriments();
        if (nutriments != null) {
            // Display energy in kJ, or convert to kcal if needed
            energy.setText(nutriments.getEnergy() + " kJ"); // or use conversion to kcal if required

            fat.setText(nutriments.getFat() + " g");
            saturatedFat.setText(nutriments.getSaturatedFat() + " g");
            carbohydrates.setText(nutriments.getCarbohydrates() + " g");
            sugars.setText(nutriments.getSugars() + " g");
            fiber.setText(nutriments.getFiber() + " g");
            proteins.setText(nutriments.getProteins() + " g");
            salt.setText(nutriments.getSalt() + " g");
        } else {
            Toast.makeText(this, "No nutrition information available.", Toast.LENGTH_SHORT).show();
        }
    }


    private void showProductNotFoundMessage() {
        Toast.makeText(this, "Product not found.", Toast.LENGTH_SHORT).show();
    }
}
