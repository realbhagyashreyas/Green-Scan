package com.example.ecofriendlyscanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    private OpenFoodFactsApi openFoodFactsApi;
    private TextView productName;
    private TextView brand;
    private TextView packaging;
    private TextView ingredients;
    private TextView nutriScore;
    private TextView ecoScore;
    private ImageView productImage;
    private ImageView nutriScoreImage;  // ImageView for Nutri-Score
    private ImageView ecoScoreImage;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Initialize the views
        productName = findViewById(R.id.product_name);
        brand = findViewById(R.id.brand);
        nutriScore = findViewById(R.id.nutri_score);
        productImage = findViewById(R.id.product_image);
        ecoScore = findViewById(R.id.eco_score);
        nutriScoreImage = findViewById(R.id.nutri_score_image); // Assuming you have an ImageView for Nutri-Score
        ecoScoreImage = findViewById(R.id.eco_score_image);
        btn = findViewById(R.id.more_info);


        openFoodFactsApi = ApiClient.getClient().create(OpenFoodFactsApi.class);



        String barcode = getIntent().getStringExtra("barcode");
        if (barcode != null) {
            fetchProductData(barcode);
        } else {
            Toast.makeText(this, "No barcode found.", Toast.LENGTH_SHORT).show();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, NutritionFactsActivity.class);
                intent.putExtra("barcode", barcode);
                startActivity(intent);

            }
        });
    }

    private void fetchProductData(String barcode) {
        openFoodFactsApi.getProductInfo(barcode).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductResponse productResponse = response.body();
                    if (productResponse.getStatus() == 1) {
                        ProductResponse.Product product = productResponse.getProduct();
                        displayProductInfo(product);
                    } else {
                        showProductNotFoundMessage();
                    }
                } else {
                    showProductNotFoundMessage();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e("API Error", "Error fetching product data", t);
                Toast.makeText(ProductDetailActivity.this, "Error fetching product data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayProductInfo(ProductResponse.Product product) {
        productName.setText("Product: " + product.getProductName());
        brand.setText("Brand: " + product.getBrands());

        // Load product image
        String imageUrl = product.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(productImage);
        } else {
            productImage.setImageResource(R.drawable.ic_placeholder);
        }

        // Display and set Nutri-Score image
        String nutriScoreValue = product.getNutriScore();
        if (nutriScoreValue != null && !nutriScoreValue.isEmpty()) {
            nutriScore.setText("Nutri-Score: " + nutriScoreValue.toUpperCase());
            setNutriScoreImage(nutriScoreValue);
        } else {
            nutriScore.setText("Nutri-Score: Not available");
            nutriScoreImage.setImageResource(0); // Clear image if not available
        }

        // Display and set Eco-Score image
        String ecoScoreValue = product.getEcoScore();
        if (ecoScoreValue != null && !ecoScoreValue.isEmpty()) {
            ecoScore.setText("Eco-Score: " + ecoScoreValue.toUpperCase());
            setEcoScoreImage(ecoScoreValue);
        } else {
            ecoScore.setText("Eco-Score: Not available");
            ecoScoreImage.setImageResource(0); // Clear image if not available
        }
    }

    private void setNutriScoreImage(String score) {
        switch (score.toUpperCase()) {
            case "A":
                nutriScoreImage.setImageResource(R.drawable.na);
                break;
            case "B":
                nutriScoreImage.setImageResource(R.drawable.nb);
                break;
            case "C":
                nutriScoreImage.setImageResource(R.drawable.nc);
                break;
            case "D":
                nutriScoreImage.setImageResource(R.drawable.nd);
                break;
            case "E":
                nutriScoreImage.setImageResource(R.drawable.ne);
                break;
            default:
                nutriScoreImage.setImageResource(0); // Clear image for invalid score
                break;
        }
    }

    private void setEcoScoreImage(String score) {
        switch (score.toUpperCase()) {
            case "A":
                ecoScoreImage.setImageResource(R.drawable.a);
                break;
            case "B":
                ecoScoreImage.setImageResource(R.drawable.b);
                break;
            case "C":
                ecoScoreImage.setImageResource(R.drawable.c);
                break;
            case "D":
                ecoScoreImage.setImageResource(R.drawable.d);
                break;
            case "E":
                ecoScoreImage.setImageResource(R.drawable.e);
                break;
            default:
                ecoScoreImage.setImageResource(0); // Clear image for invalid score
                break;
        }
    }

    private void showProductNotFoundMessage() {
        Toast.makeText(this, "Product not found.", Toast.LENGTH_SHORT).show();
    }


}
