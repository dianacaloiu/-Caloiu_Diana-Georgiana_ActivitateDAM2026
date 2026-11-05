package com.example.seminar04;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MedicamentAdapter extends BaseAdapter {
    private List<Medicament> medicamente = null;
    private Context ctx;
    private int resursaLayout;

    public MedicamentAdapter(List<Medicament> medicamente, Context ctx, int resursaLayout) {
        this.medicamente = medicamente;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return medicamente.size();
    }

    @Override
    public Object getItem(int i) {
        return medicamente.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        TextView tvStatus = v.findViewById(R.id.tvStatus);
        TextView tvDenumire = v.findViewById(R.id.tvDenumire);
        TextView tvDetalii = v.findViewById(R.id.tvDetalii);
        TextView tvPret = v.findViewById(R.id.tvPret);
        Medicament m = (Medicament) getItem(i);

        tvDenumire.setText(m.getDenumire());
        tvDetalii.setText((m.isCuReteta() ? "Cu rețetă" : "Fără rețetă") + " • " + m.getTipUtilizare());
        tvPret.setText(String.format(Locale.getDefault(), "%.2f lei", m.getPret()));
        tvStatus.setTextColor(Color.parseColor(m.isDisponibil() ? "red" : "green"));

        return v;
    }
}