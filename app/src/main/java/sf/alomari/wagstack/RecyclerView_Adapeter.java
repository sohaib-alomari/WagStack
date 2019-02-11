package sf.alomari.wagstack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.RecyclerView_Holder> {


     private static int mNumberItems;


     public static String [] gravatarArr;
     public  static String [] userName;
     public static String [] gold;
     public static String [] silver;
     public static String [] bronze;
     public static String [] location;


     RecyclerView_Adapter(String s)
     {



     }


     public static void  ExtractFeaturesFromJson(String JsonResponse) {


         try {
             JSONObject Obj = new JSONObject(JsonResponse);
             JSONArray jsonArr = Obj.getJSONArray("items");
             mNumberItems=jsonArr.length()-1;




             gravatarArr=new String[jsonArr.length()];
             userName=new String[jsonArr.length()];
             gold =new String[jsonArr.length()];
             silver=new String[jsonArr.length()];
             bronze=new String[jsonArr.length()];
             location=new String[jsonArr.length()];


             for (int i = 0; i <=mNumberItems; i++) {

                 JSONObject jsonObject = jsonArr.getJSONObject(i);

                 userName[i]=jsonObject.getString("display_name");
                 gravatarArr[i]=jsonObject.getString("profile_image");


                 //Some users are missing the whole location String Not just the value so it makes an error because the string location doesn't exist in some cases
                try {
                    location[i] = "Location: " + jsonObject.getString("location");
                }
                catch (Exception e){
                    location[i]=" Unknown location";
                }


                 gold[i]=jsonObject.getString("badge_counts");



             }




         } catch (JSONException e) {
             
             e.printStackTrace();
         }


     }








         @NonNull
    @Override
    public RecyclerView_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
             Context context=viewGroup.getContext();
             int LayoutId=R.layout.activity_recycler_view__adapeter;
             LayoutInflater inflater= LayoutInflater.from(context);
             View view=inflater.inflate(LayoutId,viewGroup,false);
             RecyclerView_Holder recHolder=new RecyclerView_Holder(view);

             return recHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_Holder holder, int position) {

         holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }


    class RecyclerView_Holder extends RecyclerView.ViewHolder

    {

        ImageView userImage;
        TextView name;
        TextView goldV;
        ProgressBar progressBar;
        TextView locationV;


        public RecyclerView_Holder(@NonNull View view) {
            super(view);

            progressBar=(ProgressBar) view.findViewById(R.id.progress_loader);

            userImage=(ImageView)view.findViewById(R.id.image);
            name=(TextView)view.findViewById(R.id.user_name);
            goldV=(TextView)view.findViewById(R.id.user_gold);
            locationV=(TextView)view.findViewById(R.id.user_location);



        }

        void bind(int position)
        {

            try{




                name.setText("Name: "+userName[position]);
                locationV.setText(location[position]);
                goldV.setText("Badges: "+gold[position]);
                progressBar.setVisibility(View.GONE);
                Picasso.get().load(gravatarArr[position]).into(userImage);




            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
