package victorflvioexamplecom.hccentrodebeleza.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import io.realm.RealmList;
import victorflvioexamplecom.hccentrodebeleza.R;
import victorflvioexamplecom.hccentrodebeleza.activity.HCCentroDeBelezaTela4;
import victorflvioexamplecom.hccentrodebeleza.extras.SavedSharedPreferences;
import victorflvioexamplecom.hccentrodebeleza.model.Reserva;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {

    private Context mContext;
    private RealmList<Reserva> mReservas;

    public ReservaAdapter(Context c, RealmList<Reserva> l) {
        this.mContext = c;
        this.mReservas = l;
    }

    @Override
    public ReservaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_reservas, parent, false);
        return new ReservaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ReservaViewHolder holder, int position) {
        holder.tvNomeServico.setText(mReservas.get(position).getNomeServico());
        holder.tvData.setText(mReservas.get(position).getData());
        holder.tvHorario.setText(mReservas.get(position).getHorario());
    }

    @Override
    public int getItemCount() {
        return mReservas.size();
    }

    public class ReservaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNomeServico, tvData, tvHorario;
        ImageButton ibEditarReserva, ibDeletarReserva;

        public ReservaViewHolder(View itemView) {
            super(itemView);

            tvNomeServico = (TextView) itemView.findViewById(R.id.tvNomeServico);
            tvHorario = (TextView) itemView.findViewById(R.id.tvHorario);
            tvData = (TextView) itemView.findViewById(R.id.tvData);

            ibEditarReserva = (ImageButton) itemView.findViewById(R.id.ibEditarReserva);
            ibDeletarReserva = (ImageButton) itemView.findViewById(R.id.ibDeletarReserva);

            ibDeletarReserva.setOnClickListener(this);
            ibEditarReserva.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ibEditarReserva:
                    SavedSharedPreferences.setNomeServico(mContext, mReservas.get(getAdapterPosition()).getNomeServico(), mReservas.get(getAdapterPosition()).getData(), mReservas.get(getAdapterPosition()).getHorario());
                    mContext.startActivity(new Intent(mContext, HCCentroDeBelezaTela4.class)
                            .putExtra("editar", true)
                            .putExtra("position", getAdapterPosition()));
                    break;
                case R.id.ibDeletarReserva:
                    break;
            }
        }
    }
}
