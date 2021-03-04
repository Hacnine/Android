package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private JsonplaceholderApi jsonplaceholderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                           .addConverterFactory(GsonConverterFactory.create())
                           .build();
        jsonplaceholderApi = retrofit.create(JsonplaceholderApi.class);

        createPost();

    }

    private void createPost() {
        Call<Post> call = jsonplaceholderApi.createPost(24,"Tusar","I am trying to do this");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code: "+response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content +="Code: "+response.code() + "\n";
                content += "ID: "+ postResponse.getId() + "\n";
                content += "User Id: "+postResponse.getUserId() + "\n";
                content += "Title: "+ postResponse.getTitle()+ "\n";
                content += "Text: "+ postResponse.getText()+ "\n";

                textView.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }
}

/*


        Call<Post> call = jsonPlaceHolderApi.createPost(23,"New Title", "New Text");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code: "+response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content +="Code: "+response.code() + "\n";
                content += "ID: "+ postResponse.getId() + "\n";
                content += "User Id: "+postResponse.getUserId() + "\n";
                content += "Title: "+ postResponse.getTitle()+ "\n";
                content += "Text: "+ postResponse.getText()+ "\n";

                textView.setText(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                textView.setText(t.getMessage());
            }
        });
    }
}


 */
