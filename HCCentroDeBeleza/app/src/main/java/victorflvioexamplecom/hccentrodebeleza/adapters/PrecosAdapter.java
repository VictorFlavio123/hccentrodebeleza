package victorflvioexamplecom.hccentrodebeleza.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import victorflvioexamplecom.hccentrodebeleza.R;
import victorflvioexamplecom.hccentrodebeleza.interfaces.RecyclerViewOnClickListenerHack;
import victorflvioexamplecom.hccentrodebeleza.model.Servicos;

public class PrecosAdapter extends RecyclerView.Adapter<PrecosAdapter.PrecosViewHolder>{

    private Context mContext;
    private ArrayList<Servicos> mServicos;

    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public PrecosAdapter(Context c, ArrayList<Servicos> l) {
        this.mContext = c;
        this.mServicos = l;
    }

    @Override
    public PrecosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_precos, parent, false);
        return new PrecosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PrecosViewHolder holder, int position) {
        holder.tvNome.setText(mServicos.get(position).getNome());
        holder.tvPreco.setText("R$" + mServicos.get(position).getPreco());
    }

    @Override
    public int getItemCount() {
        return mServicos.size();
    }

    public String returnNome(int position) {
        return mServicos.get(position).getNome();
    }

    public double returnPreco(int position) {
        return mServicos.get(position).getPreco();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        this.mRecyclerViewOnClickListenerHack = r;
    }

    public class PrecosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNome, tvPreco;

        private PrecosViewHolder(View itemView) {
            super(itemView);

            tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            tvPreco = (TextView) itemView.findViewById(R.id.tvPreco);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(view, getAdapterPosition());
            }
        }
    }
}
