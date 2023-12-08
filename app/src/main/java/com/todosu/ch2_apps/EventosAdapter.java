package com.todosu.ch2_apps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.todosu.ch2_apps.models.Evento;

import java.util.ArrayList;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {

    private ArrayList<Evento> eventos;
    private Context context;

    public EventosAdapter(Context context, ArrayList<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        holder.bindEvento(evento);
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    // Método para actualizar la lista de eventos cuando cambian los datos
    @SuppressLint("NotifyDataSetChanged")
    public void setEventos(ArrayList<Evento> nuevosEventos) {
        eventos = nuevosEventos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton btnImage;
        private TextView nEventoTextView;
        private TextView lEventoTextView;
        private TextView fEventoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnImage = itemView.findViewById(R.id.btnImageView);

            btnImage.setOnClickListener(v -> {
                // Obtener el nombre del evento para pasarlos a la nueva actividad si es necesario
                String nombreEvento = eventos.get(getAbsoluteAdapterPosition()).getNombreEvento();

                // Iniciar la nueva actividad
                Intent intent = new Intent(context, CalculadoraActivity.class);

                // Puedes agregar el nombre del evento como un extra al intent
                intent.putExtra("nombreEvento", nombreEvento);

                context.startActivity(intent);
            });
        }

        public void bindEvento(Evento evento) {

            // Inicializar las vistas
            nEventoTextView =itemView.findViewById(R.id.n_Evento);
            lEventoTextView =itemView.findViewById(R.id.l_Evento);
            fEventoTextView =itemView.findViewById(R.id.f_Evento);


            // Asignar los datos a las vistas

            if (nEventoTextView != null) {
                nEventoTextView.setText(evento.getNombreEvento());
            }
            if (lEventoTextView != null) {
                lEventoTextView.setText(evento.getLugarEvento());
            }
            if (fEventoTextView != null) {
                fEventoTextView.setText(evento.getFecha().toString());
            }

        }
    }
}
